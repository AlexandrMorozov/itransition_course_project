package com.almor.course_project.dto;

import com.almor.course_project.model.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRatingDto {

    private Long id;

    private String name;

    private String description;

    private Double rating;

    private Date lastUpdateDate;

    private User owner;

}
