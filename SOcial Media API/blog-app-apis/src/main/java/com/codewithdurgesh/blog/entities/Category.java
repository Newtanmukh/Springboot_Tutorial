package com.codewithdurgesh.blog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "title",length = 100,nullable = false)
    private String categoryTitle;

    @Column(name = "description")
    private String categoryDescription;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)//all entity operations (persist, remove, merge, refresh, etc.) will be cascaded from the source entity to the target entity.
    private List<Post>posts = new ArrayList<>();
}
