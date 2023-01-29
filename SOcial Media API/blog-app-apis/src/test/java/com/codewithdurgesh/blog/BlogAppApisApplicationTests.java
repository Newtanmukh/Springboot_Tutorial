package com.codewithdurgesh.blog;

import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Test
	public void repoTest()
	{
		String className=this.userRepo.getClass().getName();//will give you the name of the class that implements this interface
		String packageName=this.userRepo.getClass().getPackageName();//will give you the name of the package that stores the implemented class above.
		System.out.println(className);
		System.out.println(packageName);
	}

	@Test //Add these annotations as well then run this java file. the methods which have got annotation like this above will then run.
	public void userServiceTest()
	{
		String className=this.userService.getClass().getName();
		String packageName=this.userService.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);
	}

	@Test
	void contextLoads() {
	}

}
