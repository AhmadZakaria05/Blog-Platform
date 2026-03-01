package com.devtiro.blog.services;

import com.devtiro.blog.domain.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public interface TagService {
    List<Tag> getAllTags();
    List<Tag> createTags(Set<String> tagNames);
    void deleteTag(UUID id);
    Tag getTagById(UUID id);
    List<Tag> getTagsByIds(Set<UUID> ids);
}
