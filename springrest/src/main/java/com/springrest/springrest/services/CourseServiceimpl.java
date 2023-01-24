package com.springrest.springrest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.springrest.entities.Course;



@Service
public class CourseServiceimpl implements CourseService {
	
	
	
	List<Course>list;
	
	public CourseServiceimpl() {
		
		list=new ArrayList<>();
		list.add(new Course(145,"Java course course","This contains basic java stuff"));
		list.add(new Course(5432,"Springboot course","REST API using springboot"));
		
	}
	
	
	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return list;
	}


	@Override
	public Course getCourse(long courseId) {
		Course c=null;
		
		for(Course course:list)
		{
			if(course.getId()==courseId)
			{
				c=course;
				break;
			}
		}
		
		return c;
	}


	@Override
	public Course addCourse(Course course) {
		
		list.add(course);
		return course;
	}

}
