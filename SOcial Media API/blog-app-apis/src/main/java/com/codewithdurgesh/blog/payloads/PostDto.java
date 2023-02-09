package com.codewithdurgesh.blog.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postid;
    //create
    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;//using CategoryDto instead of category will solve the recursion problem. recursion probken arose due to the fact that, post inside User, then again post inside it and so on till infinite times. since categoryDto does not have any such, so it will solve the problem.

    private UserDto user;

    private Set<CommentDto> comments= new HashSet<>();//if it were comment , then post inside comment, then again comment inside that post and infinite recurison. so , since commentDto does not have any post entry, so that is why.
}
