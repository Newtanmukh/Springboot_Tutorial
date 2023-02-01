package com.codewithdurgesh.blog.controllers;

import com.codewithdurgesh.blog.services.UserService;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    //POST-create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }


    //PUT-update user
    @PutMapping("/{userId}") //here,userId is called path URL Variable.
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId)
    {
        UserDto updatedUser = this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser); //status will be OK , which will be sent
    }

    //DELETE - delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteuser(@PathVariable("userId") Integer uid) //basically,we are renaming the userId to "uid" here.
    {
        this.userService.deleteUser(uid);

        //we can return map here, like :
        //return ResponseEntity.ok(Map.of("Message","user Deleted Successfully"))

        //we will send status code instead.
        return new ResponseEntity(Map.of("Message", "user Deleted Successfully"),HttpStatus.OK);//first will be the object in key value pair,second will be status code.

        //we can also send an object of ApiResponse that is made in the payloads package.
        // return new ResponseEntity<>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
    }





    //GET - Get user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {

        return ResponseEntity.ok(this.userService.getAllUsers());
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
    {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

}
