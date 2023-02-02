package com.codewithdurgesh.blog.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;

    @NotBlank
    @Size(min = 4,message = "title should be atleast 4 characters long")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, max = 100,message = "Description must be between 10 and 100 characters long")//if not provided message then automatically a default message will run
    private String categoryDescription;
}
