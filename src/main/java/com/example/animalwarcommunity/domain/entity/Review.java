package com.example.animalwarcommunity.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JsonIgnoreProperties("reviewList")
    private Post post;

    @OneToMany(mappedBy = "review")
    private List<Reply> replyList;

    private String nickName;

    private String content;

    private String profileImage;



    @CreationTimestamp
    private LocalDateTime createAt;


}
