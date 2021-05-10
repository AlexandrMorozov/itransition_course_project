package com.almor.course_project.dto;

import com.almor.course_project.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignFileDto {

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

    private List<MultipartFile> image;

}
