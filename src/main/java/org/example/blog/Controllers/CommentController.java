package org.example.blog.Controllers;

import org.example.blog.Services.CommentService;
import org.example.blog.entities.Comment;
import org.example.blog.payloads.ApiResponse;
import org.example.blog.payloads.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto createComment=this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<CommentDto>(commentDto, HttpStatus.CREATED);
    }


    @GetMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment( @PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully !!",true),HttpStatus.OK);
    }






}
