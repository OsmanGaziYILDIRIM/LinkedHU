package com.powerrangers.linkedhu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SettingsResponseDTO {
    public String username;
    public String name;
    public String surname;
    public String birthdate;
    public Byte gender;
    public String phoneNumber;
    public Byte department;
    public String graduate_year;
    public Byte degree;
    public String text;
}