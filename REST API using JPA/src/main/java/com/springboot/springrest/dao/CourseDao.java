package com.springboot.springrest.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.springboot.springrest.entities.Course;


// two things to be given as arguments, first one is the entity which we want to deal with.
//second is the data type of the primary key of this entity. here, it is 'Long'.

@Repository
public interface CourseDao extends JpaRepository<Course, Long>{
	
	
	
}
