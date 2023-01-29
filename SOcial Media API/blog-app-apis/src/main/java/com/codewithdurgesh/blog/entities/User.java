package com.codewithdurgesh.blog.entities;

import javax.persistence.*;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





//Using LOMBOK Here.
//@Table(name="User") //basically the name of the table that will be stored in the database. by default if we don't specify this annotation, the name of this class will be taken as the name of the table.
@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
	//@GeneratedValue(strategy=GenerationType.AUTO) //basically, it will autoincrement the id.


	@javax.persistence.Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@Column(name="user_name",nullable=false,length=100) //max length of this column is 100(VARCHAR type)
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;


}
