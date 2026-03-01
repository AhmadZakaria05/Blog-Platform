package com.devtiro.blog.controllers;

import com.devtiro.blog.domain.CreatePostRequest;
import com.devtiro.blog.domain.UpdatePostRequest;
import com.devtiro.blog.domain.dto.CreatePostRequestDto;
import com.devtiro.blog.domain.dto.PostDto;
import com.devtiro.blog.domain.dto.UpdatePostRequestDto;
import com.devtiro.blog.domain.entity.Post;
import com.devtiro.blog.domain.entity.User;
import com.devtiro.blog.mappers.PostMapper;
import com.devtiro.blog.services.PostService;
import com.devtiro.blog.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID tagId) {

        List<Post> posts = postService.getAllPost(categoryId, tagId);
        List<PostDto> postDtos = posts.stream().map(postMapper::toPostDto).toList();

        return ResponseEntity.ok(postDtos);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute  UUID userId) {
        User loggedInUser = userService.getUserById(userId);
        List<Post> draftPosts = postService.getDraftPosts(loggedInUser);
        List<PostDto> postDtos = draftPosts.stream().map(postMapper::toPostDto)
                .toList();
        return ResponseEntity.ok(postDtos);

    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody CreatePostRequestDto createPostRequestDto,
            @RequestAttribute UUID userId) {

        User loggedInUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDto);
        Post newPost = postService.createPost(loggedInUser, createPostRequest);
        PostDto createdPostDto = postMapper.toPostDto(newPost);

        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    //بده مراجعه
    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto) {
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);
        Post updatedPost = postService.updatePost(id, updatePostRequest);
        PostDto updatedPostDto = postMapper.toPostDto(updatedPost);

        return ResponseEntity.ok(updatedPostDto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostDto> getPost(
            @PathVariable UUID id) {

        Post post = postService.getPost(id);
        PostDto postDto = postMapper.toPostDto(post);
        return ResponseEntity.ok(postDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

}
