package com.codewithdurgesh.blog.entities;

import javax.persistence.*;
import java.util.Date;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title",length = 500,nullable=false)
    private String title;


    private String content;

    private String imageName;

    private Date addedDate;//when this post was added.

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;//which category this post belongs to.

    @ManyToOne
    private User user;//which user had added this.

}
