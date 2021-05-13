package com.almor.course_project.dto;

import com.almor.course_project.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDto {

    private Long id;

    private UserDtoLite user;

    private String name;

    private String description;

    private String videoLink;

    private int sumOfMoney;

    private Date lastUpdateDate;

    private Date lastDateOfCampaign;

    private Set<Comment> comments;//

    private Set<TagDto> tags;

    private Set<News> news;//

    private Set<BonusDto> bonuses;

    private Set<Gallery> pictures;//

    private Topic topic;

    public void addPictures(Collection<Gallery> imageLinks) {

        if (pictures == null) {
            pictures = new HashSet<>();
        }

        pictures.addAll(imageLinks);
    }

}
