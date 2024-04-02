package org.example.blog.Service.impl;

import org.example.blog.Services.PostService;
import org.example.blog.entities.Category;
import org.example.blog.entities.Post;
import org.example.blog.entities.User;
import org.example.blog.exceptions.ResourceNotFoundException;
import org.example.blog.payloads.PostDto;
import org.example.blog.payloads.PostResponse;
import org.example.blog.repositories.CategoryRepo;
import org.example.blog.repositories.PostRepo;
import org.example.blog.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {

        User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        Category category= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost= this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);



    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
        post.setTittle(postDto.getTittle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost=this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);

    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
        this.postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir)
    {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else {
            sort=Sort.by(sortBy).descending();
        }



        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Post> postPage = this.postRepo.findAll(pageable);
        List<Post> allPosts = postPage.getContent();


//        List<Post> allPosts=this.postRepo.findAll();
        List<PostDto> postDtos= allPosts.stream().map((post) ->this.modelMapper.map(post,PostDto.class) ).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPages(postPage.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        List<Post> posts= this.postRepo.findByCategory(category);
        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        List<Post> posts= this.postRepo.findByUser(user);
        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;

    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts=this.postRepo.findByTittleContaining(keyword);
        List<PostDto> postDtos=posts.stream().map((post) ->this.modelMapper.map(post,PostDto.class )).collect(Collectors.toList());
        return postDtos;

    }
}
