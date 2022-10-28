package com.powerrangers.linkedhu.responses;

import com.powerrangers.linkedhu.entity.Comment;
import lombok.Data;
@Data
public class CommentResponse {

    Long id;
    Long userId;
    String name;
    String text;

    public CommentResponse(Comment entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.name = entity.getUser().getName()+" "+entity.getUser().getSurname();
        this.text = entity.getText();
    }
}