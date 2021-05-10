package com.almor.course_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @Override
    public boolean equals(Object object) {

        boolean equalsResult = false;

        if (object != null && object.getClass() == getClass()) {
            Role role = (Role) object;

            if (role.roleName == this.roleName) {
                return true;
            }
        }

        return equalsResult;
    }

}
