package com.codewithdurgesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codewithdurgesh.blog.entities.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<User,Integer> {  //two things as argument,which entity we want to work with and the id is of which type.


}
