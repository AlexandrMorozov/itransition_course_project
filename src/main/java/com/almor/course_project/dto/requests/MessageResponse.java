package com.almor.course_project.dto.requests;

import lombok.Data;

@Data
public class MessageResponse {

    public MessageResponse(String message) {
        this.message = message;
    }

    private String message;

}
