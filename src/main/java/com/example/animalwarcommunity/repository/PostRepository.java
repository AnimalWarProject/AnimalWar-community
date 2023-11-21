package com.example.animalwarcommunity.repository;

import com.example.animalwarcommunity.domain.entity.Post;
import com.example.animalwarcommunity.domain.response.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

   @Query("select " +
            "new " +
            "com.example.animalwarcommunity.domain.response" +
            ".PostResponse(p.postId,p.userId, p.title,p.content,p.createAt,p.postImage) " +
            "from Post p " +
            "where p.title like '%'||:content||'%' " +
            "or p.content like '%'||:content||'%' " +
           " order by p.createAt desc"
           )
   Page<PostResponse> findAll(PageRequest request,String content);
}
