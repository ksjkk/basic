package com.my.basic.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping("/api/java")
public class BasicController {

    private long Sequence = 1L;

    Logger logger = LoggerFactory.getLogger(BasicController.class);

    @GetMapping("/ping")
    public String ping(HttpServletRequest request, HttpServletResponse response){
        logger.info("ping start");
        Cookie[] cookies = request.getCookies();

        if(cookies == null){
            Cookie cookie = new Cookie("token", "create");
            cookie.setMaxAge(1000);
            response.addCookie(cookie);
            return "cookie is null, just created";
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                logger.debug("cookie value, age : {} {}", cookie.getValue(), cookie.getMaxAge());
                cookie.setValue("update");
                cookie.setMaxAge(1000);
                response.addCookie(cookie);
                break;
            }
            else{
                continue;
            }
        }
        response.addCookie(
                new Cookie("SEQ", String.valueOf(++Sequence))
        );
        logger.debug("request : {}", request);
        logger.debug("response : {}", response);
        return "pong";
    }
}
