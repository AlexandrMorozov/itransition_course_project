package com.almor.course_project.dto;

import com.almor.course_project.model.Bonus;
import com.almor.course_project.model.Campaign;
import com.almor.course_project.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String name;

    private String password;

    private String email;

    //
    private Set</*Bonus*/BonusDto> bonuses;

    //
    private Set<Role> roles;

    private Set<CampaignDto> campaigns;
}
