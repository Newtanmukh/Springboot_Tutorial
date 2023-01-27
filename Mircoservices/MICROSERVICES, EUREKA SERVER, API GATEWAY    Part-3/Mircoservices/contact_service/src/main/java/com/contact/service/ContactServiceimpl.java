package com.contact.service;

import com.contact.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceimpl implements ContactService {


    //fake list of contacts

    List<Contact>list=List.of(
            new Contact(1L,"amit@gmail.com","Amit",1311L),
            new Contact(2L,"anil@gmail.com","Anil",1312L),
            new Contact(3L,"rohan@gmail.com","rohan",1313L),
            new Contact(4L,"sameer@gmail.com","rohan",1314L),
            new Contact(5L,"ayush@gmail.com","ayush",1312L)

    );
    @Override
    public List<Contact> getContactsofUser(Long userId) {
        return list.stream().filter(contact -> contact.getUserId().equals(userId)).collect(Collectors.toList());
    }
}
