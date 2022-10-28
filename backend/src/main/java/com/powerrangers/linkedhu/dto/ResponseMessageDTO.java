package com.powerrangers.linkedhu.dto;

public class ResponseMessageDTO {
    private String message;
    public ResponseMessageDTO(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
