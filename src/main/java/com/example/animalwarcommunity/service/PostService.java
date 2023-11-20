package com.example.animalwarcommunity.service;


import com.example.animalwarcommunity.domain.entity.Post;
import com.example.animalwarcommunity.domain.entity.Reply;
import com.example.animalwarcommunity.domain.entity.Review;
import com.example.animalwarcommunity.domain.request.PostRequest;
import com.example.animalwarcommunity.domain.request.PostUpdateRequest;
import com.example.animalwarcommunity.domain.request.ReviewRequest;
import com.example.animalwarcommunity.domain.response.PostResponse;
import com.example.animalwarcommunity.repository.PostRepository;
import com.example.animalwarcommunity.repository.ReplyRepository;
import com.example.animalwarcommunity.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ReviewRepository reviewRepository;
    private final ReplyRepository replyRepository;

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

    public Page<PostResponse> getAllPost(PageRequest pageRequest) {
        return postRepository.findAll(pageRequest);
    }

    public void writeReview(Long postId, String nickname, ReviewRequest req){
        Post post = postRepository.findById(postId).get();
        Review review = Review.builder()
                .post(post)
                .nickName(nickname)
                .content(req.getContent())
                .profileImage(req.getProfileImage())
                .build();

        reviewRepository.save(review);
    }

    public void writeReply(Long reviewId,String nickname,ReviewRequest req){

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("no Reply"));

        Reply reply = Reply.builder()
                .review(review)
                .nickName(nickname)
                .content(req.getContent())
                .profileImage(req.getProfileImage())
                .build();

        replyRepository.save(reply);
    }
}


