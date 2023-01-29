package com.codewithdurgesh.blog.exceptions;


import com.codewithdurgesh.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //It allows to handle exceptions across the whole application in one global handling component.
public class GlobalExceptionHandler {


    //what will happen is, throughout our project, whenever we encounter the resourceNotFoundException, this method will be fired.
    @ExceptionHandler(ResourceNotFoundException.class) //we had created this class called ResourceNotFound manually.WE HAD NOT used any prebuilt library here. whenever we enocunter the resourcenotfound exception, this method will run.
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){

        String message=ex.getMessage();//we will get the message from that thing. remember, user with userid :4 not found/ that message we are talking about.
        ApiResponse apiResponse=new ApiResponse(message,false);

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
