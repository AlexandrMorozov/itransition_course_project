package com.almor.course_project.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {

    public Role() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roleName;
}
