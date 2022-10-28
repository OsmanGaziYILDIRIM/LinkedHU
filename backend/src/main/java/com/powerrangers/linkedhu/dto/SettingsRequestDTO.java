package com.powerrangers.linkedhu.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SettingsRequestDTO {
    @NotEmpty
    private Long userId;

    private String password;
    private String name;
    private String surname;
    private String birthdate;
    private String phoneNumber;
    private Byte gender;
    private Byte department;
    private String graduate_year;
    private Byte degree;
    private String text;
}
