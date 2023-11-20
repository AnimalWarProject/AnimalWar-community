package com.example.animalwarcommunity.domain.entity;

import com.example.animalwarcommunity.repository.ReviewRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;


    @ManyToOne
    @JsonIgnoreProperties("replyList")
    private Review review;

    private String nickName;

    private String content;

    private String profileImage;

    @CreationTimestamp
    private LocalDateTime createAt;
}
