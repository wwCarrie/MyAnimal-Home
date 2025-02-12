package com.example.animalhome.entity;

import java.io.Serializable;

public class AnimalKnowledge implements Serializable {

    private String title;

    private String content;

    private Integer imgResId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getImgResId() {
        return imgResId;
    }

    public void setImgResId(Integer imgResId) {
        this.imgResId = imgResId;
    }
}
