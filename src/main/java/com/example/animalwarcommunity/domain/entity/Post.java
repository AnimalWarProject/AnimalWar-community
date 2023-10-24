package com.example.animalwarcommunity.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="posts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    private String userId;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private String postImage;
}
