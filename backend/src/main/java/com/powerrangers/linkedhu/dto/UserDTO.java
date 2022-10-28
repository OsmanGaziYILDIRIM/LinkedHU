package com.powerrangers.linkedhu.dto;

import lombok.*;

import javax.persistence.Access;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String fullname;
    private String roles;
    private String birthdate;
    private String phoneNumber;
    private Byte gender;
    private String biography;
    private Byte degree;
    private Byte department;
    private String graduate_year;
    private Boolean enable;
    private Boolean banned;
}
