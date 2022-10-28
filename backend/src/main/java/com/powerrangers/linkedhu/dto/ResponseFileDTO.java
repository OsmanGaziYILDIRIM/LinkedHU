package com.powerrangers.linkedhu.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseFileDTO {
    private String name;
    private String url;
    private String type;
    private long size;
    private Long owner_id;
    public ResponseFileDTO(String name, String url, String type, long size,Long owner_id) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.owner_id=owner_id;
    }
}
