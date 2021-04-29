package com.almor.course_project.dto.requests;

import lombok.Data;

@Data
public class LoginRequest {

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;

    private String password;

}
