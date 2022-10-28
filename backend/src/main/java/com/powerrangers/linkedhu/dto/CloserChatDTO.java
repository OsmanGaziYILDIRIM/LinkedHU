package com.powerrangers.linkedhu.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CloserChatDTO {
    private Long userId;
    private String fullname;
    private String lastmessage;
    private Date date;
}
