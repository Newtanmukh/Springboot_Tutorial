package com.codewithdurgesh.blog.payloads;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

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

}
