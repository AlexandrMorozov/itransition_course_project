package com.almor.course_project.dto.requests;

import lombok.Data;

@Data
public class SigninRequest {

    //Think about role distribution
    private String username;

    private String password;

    private String email;

}
