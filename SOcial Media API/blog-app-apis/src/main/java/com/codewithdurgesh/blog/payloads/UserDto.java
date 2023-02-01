package com.codewithdurgesh.blog.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty  //check for not null and empty both
    @Size(min = 4,message = "Name must be atleast of 4 characters")//this message will go incase the size<4.
    private String name;

    @Email(message = "YOur given email address is not valid") //if email validation fail, send this message.
    private String email;

    @NotEmpty
    @Size(min = 3,max = 10,message = "Password must be minimum of 3 chars and maximum of 10 chars.")
    private String password;

    @NotEmpty
    private String about;

}
