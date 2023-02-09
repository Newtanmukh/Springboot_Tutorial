package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.payloads.CommentDto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto, Integer postId);
    public void deleteComment(Integer commentId);
    public List<CommentDto>getAllComments();
    public CommentDto getCommentById(Integer commentId);
}
