package org.example.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor

public class PostResponse {
    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private long TotalElements;
    private int TotalPages;
    private boolean LastPages;
}
