package com.example.animalwarcommunity.domain.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostRequest {
    private String title;
    private String content;
    private String postImage;
}
