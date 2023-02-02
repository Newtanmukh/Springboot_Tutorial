package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create
    public CategoryDto createCategory(CategoryDto categoryDto);

    //update
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryid);

    //delete
    public void deleteCategory(Integer categoryId);

    //get
    public CategoryDto getCategory(Integer categoryId);

    //get all categories
    public List<CategoryDto> getCategories();
}
