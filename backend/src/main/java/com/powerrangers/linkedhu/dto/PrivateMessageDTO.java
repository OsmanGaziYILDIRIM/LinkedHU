package com.powerrangers.linkedhu.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Data
@RequiredArgsConstructor
public class PrivateMessageDTO {

    private Long id;

    @NotEmpty
    private String senderUsername;
    @NotEmpty
    private String receiverUsername;
    @NotEmpty
    private String content;

    private Date date;
}
