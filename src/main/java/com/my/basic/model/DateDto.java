package com.my.basic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@ToString
@NoArgsConstructor
public class DateDto {
    private LocalDateTime localDateTime;
    private ZonedDateTime zonedDateTime;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        System.out.println("localDateTime = " + localDateTime);
        this.localDateTime = localDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        System.out.println("zonedDateTime = " + zonedDateTime);
        this.zonedDateTime = zonedDateTime;
    }
}
