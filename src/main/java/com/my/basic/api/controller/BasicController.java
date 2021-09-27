package com.my.basic.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("api")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class BasicController {

    private long Sequence = 1L;

    Logger logger = LoggerFactory.getLogger(BasicController.class);

    @RequestMapping
    public void all(HttpServletRequest request){
    }

    @GetMapping("/ping")
    public String ping(HttpServletRequest request, HttpServletResponse response){
        logger.info("ping start");
        return "pong";
    }

    @GetMapping("/auth/token")
    public String getToken(HttpServletRequest request){
        logger.info("get token start");
        String authorization = request.getHeader("Authorization");
        logger.info("auth : {}", authorization);

        String uuid = UUID.randomUUID().toString();

        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return uuid;
        }

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("auth")){
                uuid = cookie.getValue();
                break;
            }
        }

        return uuid;
    }

    @GetMapping("/auth/response")
    public void flushResponse(HttpServletRequest request, HttpServletResponse response){
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                logger.debug("cookie (name, value) is ({},{})", cookie.getName(), cookie.getValue());
            }
        }
        catch (Exception e){

        }
        Cookie cookie = new Cookie("auth", UUID.randomUUID().toString());
        response.addCookie(cookie);

    }
}
