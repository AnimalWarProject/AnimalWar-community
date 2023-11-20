package com.example.animalwarcommunity.domain.response;

import com.example.animalwarcommunity.domain.entity.Post;
import com.example.animalwarcommunity.domain.entity.Review;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PostResponse {

    private long postId;
    private String userId;
    private String title;
    private String content;
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

