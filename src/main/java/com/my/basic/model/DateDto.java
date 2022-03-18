package com.my.basic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter(onMethod_ = {@TestAnnotation, @JsonProperty})
@ToString
@NoArgsConstructor
public class DateDto {
    private LocalDateTime localDateTime;
    private ZonedDateTime zonedDateTime;
    private String string;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        System.out.println("==================================");
        System.out.println("localDateTime = " + localDateTime.atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime());
        this.localDateTime = localDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        System.out.println("==================================");
        System.out.println("before set zone zonedDateTime = " + zonedDateTime);
        zonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));
        System.out.println("after set zone zonedDateTime = " + zonedDateTime);
        System.out.println("zoned to localDateTime = " + zonedDateTime.toLocalDateTime());
        this.zonedDateTime = zonedDateTime;
    }

    public void setString(String string) {
        System.out.println("==================================");
        System.out.println("string = " + string);
        this.string = string;
    }
}
