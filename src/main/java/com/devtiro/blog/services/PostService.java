package com.devtiro.blog.services;

import com.devtiro.blog.domain.CreatePostRequest;
import com.devtiro.blog.domain.UpdatePostRequest;
import com.devtiro.blog.domain.entity.Post;
import com.devtiro.blog.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PostService {
    Post getPost(UUID id);
    List<Post> getAllPost(UUID  categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest  createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
    void deletePost(UUID id);

}
