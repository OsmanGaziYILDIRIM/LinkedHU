package com.powerrangers.linkedhu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCommentDTO {
    private Long postId;
    private String text;
    private Long userId;
}
