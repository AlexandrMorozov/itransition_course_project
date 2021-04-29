package com.almor.course_project.model;

import com.almor.course_project.model.composite_tables.UsersRatings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private String videoLink;

    private int sumOfMoney;

    @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;

    @Temporal(TemporalType.DATE)
    private Date lastDateOfCampaign;

    @OneToMany(targetEntity = Comment.class)
    @JoinColumn(name = "comment_id")
    private Set<Comment> comments;

    @OneToMany(targetEntity = Tag.class)
    @JoinColumn(name = "tag_id")
    private Set<Tag> tags;

    @OneToMany(targetEntity = News.class)
    @JoinColumn(name = "news_id")
    private Set<News> news;

    @OneToMany(targetEntity = Bonus.class)
    @JoinColumn(name = "bonus_id")
    private Set<Bonus> bonuses;

    @OneToMany(targetEntity = Gallery.class)
    @JoinColumn(name = "picture_id")
    private Set<Gallery> pictures;

    @ManyToOne(targetEntity = Topic.class)
    private Topic topic;

}
