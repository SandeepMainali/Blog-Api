package org.example.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.blog.entities.Category;
import org.example.blog.entities.Comment;
import org.example.blog.entities.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class PostDto {


    private Integer PostId;
    private String tittle;
    private String content;
    private String imageName;
    private Date addDate;
    private CategoryDto category;
    private UserDto user;
    private Set<CommentDto> comments= new HashSet<>();



}
