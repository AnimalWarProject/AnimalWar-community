package com.example.animalwarcommunity.controller;

import com.example.animalwarcommunity.config.JwtService;
import com.example.animalwarcommunity.config.TokenInfo;
import com.example.animalwarcommunity.domain.entity.Post;
import com.example.animalwarcommunity.domain.request.PostRequest;
import com.example.animalwarcommunity.domain.request.PostUpdateRequest;
import com.example.animalwarcommunity.domain.request.ReviewRequest;
import com.example.animalwarcommunity.domain.response.PostResponse;
import com.example.animalwarcommunity.repository.PostRepository;
import com.example.animalwarcommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;
    private final JwtService jwtService;


    @GetMapping()
    public Page<PostResponse> allPost(
            @RequestParam(
                    required = false,
                    defaultValue = "0",
                    name = "page"
            ) Integer page,
            @RequestParam(
                    required = false,
                    defaultValue = "10",
                    name = "size"
            ) Integer size,
            @RequestParam(
                    required = false,
                    defaultValue = "",
                    name = "content"
            ) String content
    ){
        return postService.getAllPost(PageRequest.of(page,size),content);
    }




    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest,
                                             @RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseAccessToken(token.replace("Bearer ", ""));
        String userId = tokenInfo.getUserId();

        postService.createPost(userId, postRequest);

        return ResponseEntity.ok("Post Created");
    }


    @PostMapping("/update")
    public ResponseEntity<String> updatePost(@RequestBody PostUpdateRequest postUpdateRequest,
                                             @RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseAccessToken(token.replace("Bearer ", ""));

        postService.updatePost(postUpdateRequest);
        return ResponseEntity.ok("Post Updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePost(@RequestBody Long postId,
                                           @RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseAccessToken(token.replace("Bearer ", ""));

        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/{postId}")
    public ResponseEntity<Post> searchPost(@PathVariable Long postId) {
        Post post = postService.searchPost(postId);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/search/{postId}")
    public ResponseEntity<String> writeReview(@PathVariable Long postId
            , @RequestHeader("Authorization") String token
            , @RequestBody ReviewRequest reviewRequest){
        TokenInfo tokenInfo = jwtService.parseAccessToken(token.replace("Bearer ", ""));

        postService.writeReview(postId,tokenInfo.getNickName(),reviewRequest);

        return ResponseEntity.ok("Write Review");
    }

    @PostMapping("/reply/{reviewId}")
    public ResponseEntity<String> writeReply(
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String token
            , @RequestBody ReviewRequest reviewRequest){
        TokenInfo tokenInfo = jwtService.parseAccessToken(token.replace("Bearer ", ""));

        postService.writeReply(reviewId,tokenInfo.getNickName(),reviewRequest);

        return ResponseEntity.ok("Write Review");
    }

}
