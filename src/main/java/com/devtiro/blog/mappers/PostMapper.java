package com.devtiro.blog.mappers;

import com.devtiro.blog.domain.CreatePostRequest;
import com.devtiro.blog.domain.UpdatePostRequest;
import com.devtiro.blog.domain.dto.CreateCategoryRequest;
import com.devtiro.blog.domain.dto.CreatePostRequestDto;
import com.devtiro.blog.domain.dto.PostDto;
import com.devtiro.blog.domain.dto.UpdatePostRequestDto;
import com.devtiro.blog.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        uses = {CategoryMapper.class, TagMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toPostDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);
    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);
}
