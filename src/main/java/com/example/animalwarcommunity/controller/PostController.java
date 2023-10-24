package com.example.animalwarcommunity.controller;

import com.example.animalwarcommunity.config.JwtService;
import com.example.animalwarcommunity.config.TokenInfo;
import com.example.animalwarcommunity.domain.entity.Post;
import com.example.animalwarcommunity.domain.request.PostRequest;
import com.example.animalwarcommunity.domain.request.PostUpdateRequest;
import com.example.animalwarcommunity.repository.PostRepository;
import com.example.animalwarcommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;
    private final JwtService jwtService;


    @PostMapping("/create")
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

}
