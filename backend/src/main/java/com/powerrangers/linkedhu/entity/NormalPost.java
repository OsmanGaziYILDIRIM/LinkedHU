package com.powerrangers.linkedhu.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Data
public class NormalPost extends Post{

    private String title;
    private String text;

}