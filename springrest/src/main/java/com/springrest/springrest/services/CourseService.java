package com.springrest.springrest.services;
import java.util.List;



import com.springboot.springrest.entities.Course;


public interface CourseService {
	
	public List<Course> getCourses();
	
	public Course getCourse(long courseId);
	
	public Course addCourse(Course course);
}
