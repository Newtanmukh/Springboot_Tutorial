package com.codewithdurgesh.blog.services.impl;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.*;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceimpl implements UserService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserRepo userRepo;//we have not even created the implementation of the interface called UserRepo here.
    //then how come can we use the object of the implementation clas.
    //the thing is at runtime, spring scans all the repository interface (be it JPA or CRUD repository stuff)
    //at runtime, the implementation classes of these repository are provided. these are called 'proxy' classes and are kept into a package.
    //when we do autowirded, object of this class gets injected the the userRepo variablle.

    @Autowired
    private ModelMapper modelMapper;//spring will detecct the bean from the main app and will inject its obkect here.

    @Override
    public UserDto createUser(UserDto userDto){
        User user=this.dtoTouser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto,Integer userId){

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," id ",userId));//whenever this exception comes, the GlobalExceptionHandler will be fired

        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());

        User updatedUser=this.userRepo.save(user);
        UserDto userDto1=this.userToDto(updatedUser);


        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId){

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," id ",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> users=this.userRepo.findAll();//findAll() method will return a list.
        List<UserDto>userDtos = users.stream().map(user->userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId){
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," id ",userId));
        this.userRepo.delete(user);
    }

    private User dtoTouser(UserDto userDto)
    {
        User user=this.modelMapper.map(userDto,User.class); //two arguments, first the object name which needs to be converted and second the name of the class to which it need to be converted.
        /* user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());*/
        return user;
    }

    private UserDto userToDto(User user)
    {
        UserDto userDto=this.modelMapper.map(user,UserDto.class);

        return userDto;
    }
}
