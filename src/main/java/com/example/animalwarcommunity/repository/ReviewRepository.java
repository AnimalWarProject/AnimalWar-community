package com.example.animalwarcommunity.repository;

import com.example.animalwarcommunity.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {


    List<Review> findAllByPost_PostId(Long postId);
}
