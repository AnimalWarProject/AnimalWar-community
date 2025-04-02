package com.example.animalwarcommunity.domain.response;

import com.example.animalwarcommunity.domain.entity.Post;
import com.example.animalwarcommunity.domain.entity.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PostResponse {

    private long postId;
    private String userId;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul")
    private LocalDateTime createAt;
    private String postImage;

    public void toResponse(Post post){
        postId=post.getPostId();
        userId=post.getUserId();
        title=post.getTitle();
        content=post.getContent();
        createAt=post.getCreateAt();
        postImage=post.getPostImage();
    }
}

