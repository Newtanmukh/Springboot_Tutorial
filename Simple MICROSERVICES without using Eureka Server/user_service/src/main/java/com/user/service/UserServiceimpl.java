package com.user.service;
import com.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService
{

    //MAKE FAKE USERLIST.
    List<User> list = List.of(
            new User(1311L,"Durgest tiwari","839048901413"),
            new User(1312L,"newtan","111111111"),
            new User(1313L,"akshay","98472938233"),
            new User(1314L,"nev","4323")
    );

    @Override
    public User getUser(Long id)
    {
        return this.list.stream().filter(user->user.getUserId().equals(id)).findAny().orElse(null);
    }
}
