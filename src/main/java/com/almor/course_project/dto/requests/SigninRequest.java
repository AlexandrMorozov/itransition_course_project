package com.almor.course_project.dto.requests;

import lombok.Data;

@Data
public class SigninRequest {

    public SigninRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //Think about role distribution
    private String username;

    private String password;

    private String email;

}
