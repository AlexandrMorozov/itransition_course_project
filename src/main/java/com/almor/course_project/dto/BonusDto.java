package com.almor.course_project.dto;

import com.almor.course_project.model.Campaign;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BonusDto {

    private Long id;

  /*  @JsonIgnore
    private CampaignDto campaign;*/

    private String name;

    private int sum;

    private String description;

}
