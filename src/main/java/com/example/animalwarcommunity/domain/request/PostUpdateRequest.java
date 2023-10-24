package com.example.animalwarcommunity.domain.request;

import lombok.Data;

@Data
public class PostUpdateRequest {
    private long postId;
    private String title;
    private String content;
    private String postImage;
}
