package com.springrest.springrest.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.springrest.dao.CourseDao;
import com.springboot.springrest.entities.Course;



@Service
public class CourseServiceimpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	
	
	public CourseServiceimpl() 
	{
	}
	
	
	@Override
	public List<Course> getCourses() 
	{	
		return courseDao.findAll();
	}


	@Override
	public Course getCourse(long courseId) {
		return courseDao.getReferenceById(courseId);
	}


	@Override
	public Course addCourse(Course course) {
		
		//list.add(course);
		courseDao.save(course);
		return course;
	}


	@Override
	public Course updateCourse(Course course) {
		courseDao.save(course);//this will simply update the course details
		return course;
	}


	@Override
	public Course deleteCourse(long courseId) {
       Course one=courseDao.getReferenceById(courseId);
       courseDao.delete(one);
       return one;
	}

}
