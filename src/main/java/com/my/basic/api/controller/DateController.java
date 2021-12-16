package com.my.basic.api.controller;

import com.my.basic.model.DateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RequiredArgsConstructor
public class DateController {

    @PostMapping("/log-date-type")
    public void logDate(@RequestBody DateDto dto){
        System.out.println("dto = " + dto);
    }
}
