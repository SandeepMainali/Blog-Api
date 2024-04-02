package org.example.blog.Controllers;

import jakarta.persistence.Access;
import jakarta.servlet.http.HttpServletResponse;
import org.example.blog.Services.FileService;
import org.example.blog.Services.PostService;
import org.example.blog.entities.Post;
import org.example.blog.payloads.ApiResponse;
import org.example.blog.payloads.CategoryDto;
import org.example.blog.payloads.PostDto;
import org.example.blog.payloads.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")

public class PostController {

    @Value("${project.image}")
    private String path;

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

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
                                                   @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue = "PostId",required = false)String sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir)

    {
        PostResponse allPost=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
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
    @GetMapping("/post/search/{keywords}")

    public ResponseEntity<List<PostDto>> searchByTittle(@PathVariable String keywords){
       List<PostDto> result= this.postService.searchPost(keywords);
       return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);

    }

    @PostMapping("post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile File,
                                                   @PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String filename = this.fileService.uploadImage(path,File);
        postDto.setImageName(filename);
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }
    @GetMapping(value = "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void  ViewImage(@PathVariable("imageName")String imageName, HttpServletResponse response)throws IOException{
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
