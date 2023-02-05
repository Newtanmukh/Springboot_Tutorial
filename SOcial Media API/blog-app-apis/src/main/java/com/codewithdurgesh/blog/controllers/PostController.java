package com.codewithdurgesh.blog.controllers;


import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;


    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId)
    {
        PostDto createdPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createdPost,HttpStatus.CREATED);
    }

    //get posts by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
    {
        List<PostDto>postDtos=this.postService.getPostsByUser(userId);
        return new  ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    //get posts by Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto>postDtos=this.postService.getPostsByCategory(categoryId);
        return new  ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,//required=false means that if you dont supply the pageNumber value, then also fine since it will take the default value in that case.
            @RequestParam(value = "pageSize",defaultValue = "4",required = false)Integer pageSize)
    {
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @GetMapping("posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
    {
        PostDto postDto=this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId)
    {
        this.postService.deletePost(postId);
        return new ApiResponse("Post deleted successfully",true);
    }

    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
    {
        PostDto UpdatedPostDto=this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(UpdatedPostDto,HttpStatus.OK);
    }

}
