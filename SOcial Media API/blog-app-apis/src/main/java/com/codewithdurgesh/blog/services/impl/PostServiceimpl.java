package com.codewithdurgesh.blog.services.impl;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceimpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId){

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto,Integer postId){
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        //post.setCategory(postDto.getCategory()); we cannot do this, since in Post the category column is of type category and here in postDto, it is of Type CategoryDto. similarly with User, in postdto it is of type UserDto.
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }


    public void deletePost(Integer postId){
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
        this.postRepo.delete(post);
    }


    public List<PostDto> getAllPost(){
        return this.postRepo.findAll().stream().map(post -> (this.modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
    }


    public PostDto getPostById(Integer postId)
    {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
        return this.modelMapper.map(post,PostDto.class);
    }


    public List<PostDto> getPostsByCategory(Integer categoryId){
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        List<Post>posts = this.postRepo.findByCategory(cat);

        List<PostDto>postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }


    public List<PostDto> getPostsByUser(Integer userId){
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
        List<Post>posts = this.postRepo.findByUser(user);
        List<PostDto>postDtos = posts.stream().map((post -> this.modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
        return postDtos;
    }


    public List<PostDto> searchPosts(String keyword){
        return null;
    }
}
