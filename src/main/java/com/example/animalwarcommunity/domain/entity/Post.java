package com.example.animalwarcommunity.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratedColumn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="posts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    private String userId;
    private String title;
    private String content;

    @CreationTimestamp
    private LocalDateTime createAt;
    private String postImage;

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties("post")
    private List<Review> reviewList;
}
