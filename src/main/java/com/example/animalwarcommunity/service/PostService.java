package com.example.animalwarcommunity.service;


import com.example.animalwarcommunity.domain.entity.Post;
import com.example.animalwarcommunity.domain.request.PostRequest;
import com.example.animalwarcommunity.domain.request.PostUpdateRequest;
import com.example.animalwarcommunity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(String userId, PostRequest postRequest) {
        Post post = Post.builder()
                .userId(userId)
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postImage(postRequest.getPostImage())
                .createAt(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }

    public Post updatePost(PostUpdateRequest postUpdateRequest){
        Post existingPost = postRepository.findById(postUpdateRequest.getPostId())
                .orElseThrow(()-> new RuntimeException("No Post"));

        existingPost.setTitle(postUpdateRequest.getTitle());
        existingPost.setContent(postUpdateRequest.getContent());
        existingPost.setPostImage(postUpdateRequest.getPostImage());

        return postRepository.save(existingPost);
    }


    public void deletePost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        Post post = optionalPost.orElseThrow(() -> new RuntimeException("No Post"));
        postRepository.delete(post);
    }

    public Post searchPost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        return optionalPost.orElseThrow(() -> new RuntimeException("No Post"));
    }
}


