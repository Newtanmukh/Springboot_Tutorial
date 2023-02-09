package com.codewithdurgesh.blog.repositories;
import com.codewithdurgesh.blog.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {


    //similarly,if the post's title contains this as substring, then those post will be appended.
    //SEARCHING purposes.
    @Query("select p from Post p where p.title like :key")
    List<Post>searchByTitle(@Param("key") String title);

    List<Post> findByUser(User user);





    /*This method will retrieve a list of Post objects
     based on the Category object provided as an argument.
     The Spring Data JPA framework will automatically
     generate the implementation of this method that
     retrieves the relevant data from the database based
     on the relationship between the Post
     and Category entities.

     Note that you need to have an entity defined for
     Category as well for this to work. Also, make sure
     that the relationship between Post and Category
     is correctly defined in your entity classes
      and mapped in your database.*/

    List<Post> findByCategory(Category category);

}
