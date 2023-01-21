package com.springboot.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springrest.entities.Course;
import com.springrest.springrest.services.CourseServiceimpl;

import java.util.*;

@RestController
public class MyController {

    
	public CourseServiceimpl courseService = new CourseServiceimpl();
	
	
	//get the courses
	//NOTE : Here, we will not directly return the data. we will instead consult the service layer for the same.
	
	@GetMapping("/courses")
	public List<Course> getCourses()
	{
		return this.courseService.getCourses();
	}
	
}
