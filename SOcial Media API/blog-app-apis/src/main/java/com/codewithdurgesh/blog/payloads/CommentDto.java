package com.codewithdurgesh.blog.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentDto {

    private int id;//i do not wish to show the client that like, which post this comment belongs to.

    private String content;

}
