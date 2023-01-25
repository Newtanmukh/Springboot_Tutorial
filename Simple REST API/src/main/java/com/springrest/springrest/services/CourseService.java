package com.springrest.springrest.services;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.springrest.entities.Course;


public interface CourseService {
	
	public List<Course> getCourses();
	
	public Course getCourse(long courseId);
	
	public Course addCourse(Course course);
	
	public Course updateCourse(Course course);
	
	public Course deleteCourse(long courseId);
}
