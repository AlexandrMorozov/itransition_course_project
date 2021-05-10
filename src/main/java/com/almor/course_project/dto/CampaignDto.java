package com.almor.course_project.dto;

import com.almor.course_project.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDto {

    private long id;

    private UserDtoLite user;

    private String name;

    private String description;

    private String videoLink;

    private int sumOfMoney;

    private Date lastUpdateDate;

    private Date lastDateOfCampaign;

    private Set<Comment> comments;

    private Set<Tag> tags;

    private Set<News> news;

    private Set<Bonus> bonuses;

    private Set<Gallery> pictures;

    private Topic topic;

}
