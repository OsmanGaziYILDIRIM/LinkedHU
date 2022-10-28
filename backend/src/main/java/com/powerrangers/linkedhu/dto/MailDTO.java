package com.powerrangers.linkedhu.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {
    @NotEmpty
    String subject;

    @NotEmpty
    String content;
}
