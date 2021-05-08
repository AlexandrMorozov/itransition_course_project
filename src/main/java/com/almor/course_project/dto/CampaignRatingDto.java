package com.almor.course_project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRatingDto {

    private Long id;

    private String name;

    private String description;

    private Double rating;


}
