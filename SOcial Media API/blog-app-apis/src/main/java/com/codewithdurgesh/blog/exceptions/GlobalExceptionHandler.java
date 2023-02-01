package com.codewithdurgesh.blog.exceptions;


import com.codewithdurgesh.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //It allows to handle exceptions across the whole application in one global handling component.
public class GlobalExceptionHandler {


    //what will happen is, throughout our project, whenever we encounter the resourceNotFoundException, this method will be fired.
    @ExceptionHandler(ResourceNotFoundException.class) //we had created this class called ResourceNotFound manually.WE HAD NOT used any prebuilt library here. whenever we enocunter the resourcenotfound exception, this method will run.
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){

        String message=ex.getMessage();//we will get the message from that thing. remember, user with userid :4 not found/ that message we are talking about.
        ApiResponse apiResponse=new ApiResponse(message,false);

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //this error class type WAS NOT implemented by us. it comes naturally when validation does not occur.
    public ResponseEntity<Map<String,String>>handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
    {
        //field and their message , need to be taken out.
        //like if the error is of name, then take the field 'name' and the error message associated with it.
        Map<String ,String >resp=new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldname = ((FieldError)error).getField();//we will have to typecast the error to FieldError type object
            String fieldmessage=error.getDefaultMessage();
            resp.put(fieldname, fieldmessage);
        });

        return new ResponseEntity<Map<String ,String >> (resp,HttpStatus.BAD_REQUEST);
    }
}
