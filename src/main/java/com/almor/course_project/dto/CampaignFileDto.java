package com.almor.course_project.dto;

import com.almor.course_project.model.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignFileDto {


    private long id;

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

    private List<MultipartFile> images;

    private Topic topic;

}
