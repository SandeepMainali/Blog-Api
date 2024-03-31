package org.example.blog.Services;


import org.example.blog.payloads.PostDto;
import org.example.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);


    PostDto getPostById(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);

}
