package com.my.basic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@ToString
public class BasicDto {

    @NotBlank(message = "it must not null")
    private String notNullText = "";

    private String nullableText = "";
}
