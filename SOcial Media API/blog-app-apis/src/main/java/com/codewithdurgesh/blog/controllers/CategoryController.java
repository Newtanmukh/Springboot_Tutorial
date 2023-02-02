package com.codewithdurgesh.blog.controllers;


import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("catId") Integer cId)
    {
        CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto,cId);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer cId)
    {
        this.categoryService.deleteCategory(cId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully",true),HttpStatus.OK );
    }

    //get
    @GetMapping ("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer cId)
    {
        CategoryDto categoryDto = this.categoryService.getCategory(cId);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK );
    }

    //get all
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories()
    {
        List<CategoryDto>categoryDtos=this.categoryService.getCategories();
        return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
    }


}
