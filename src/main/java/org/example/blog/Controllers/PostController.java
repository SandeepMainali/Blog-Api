package org.example.blog.Controllers;

import jakarta.persistence.Access;
import org.example.blog.Services.PostService;
import org.example.blog.entities.Post;
import org.example.blog.payloads.ApiResponse;
import org.example.blog.payloads.CategoryDto;
import org.example.blog.payloads.PostDto;
import org.example.blog.payloads.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){

    PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
    return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);


    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> posts=this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK );

    }

    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts=this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK );

    }

    //getallpost
    @GetMapping("/post")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize)
    {
        PostResponse allPost=this.postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
    }

    //getSinglePost
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto=this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);


    }

    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is Successfully Deleted !!",true);

    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatedPost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto postDto1=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);

    }



}
