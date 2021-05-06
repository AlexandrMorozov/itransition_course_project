package com.almor.course_project.model;

import com.almor.course_project.model.composite_tables.UsersRatings;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private String name;

    private String description;

    private String videoLink;

    private int sumOfMoney;

    @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;

    @Temporal(TemporalType.DATE)
    private Date lastDateOfCampaign;

    @OneToMany(targetEntity = Comment.class, mappedBy = "campaign")
    private Set<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "campaigns_tags",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(targetEntity = News.class, mappedBy = "campaign")
    private Set<News> news;

    @OneToMany(targetEntity = Bonus.class, mappedBy = "campaign")
    private Set<Bonus> bonuses;

    @OneToMany(targetEntity = Gallery.class, mappedBy = "campaign")
    private Set<Gallery> pictures;

}
