package com.my.basic.api.controller;

import com.my.basic.config.validate.CustomValidate;
import com.my.basic.model.BasicDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("api")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RequiredArgsConstructor
public class BasicController {

    private long Sequence = 1L;
    private final CustomValidate customValidate;

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

    @PostMapping("/validate-collection")
    public void validateCollection(@RequestBody @Valid List<BasicDto> list, BindingResult result){
        logger.debug("================================= validate collection start =================================");
        customValidate(list, result);
    }

    @PostMapping("/validate-dto")
    public void validateDto(@RequestBody @Valid BasicDto dto){
        logger.debug("================================= validate dto start =================================");
        System.out.println(dto.toString());
    }

    private void customValidate(Object object, BindingResult result){
        customValidate.validate(object, result);
        if(result.hasErrors()){
            for (ObjectError allError : result.getAllErrors()) {
                logger.error("allError.getDefaultMessage() = {}", allError.getDefaultMessage());
                throw new IllegalArgumentException(allError.getDefaultMessage());
            }
        }
    }
}
