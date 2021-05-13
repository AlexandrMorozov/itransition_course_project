package com.almor.course_project.model;

import com.almor.course_project.model.composite_tables.UsersRatings;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
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

    @OneToMany(targetEntity = Comment.class, mappedBy = "campaign",
            cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(	name = "campaigns_tags",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(targetEntity = News.class, mappedBy = "campaign",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<News> news;

    @OneToMany(targetEntity = Bonus.class, mappedBy = "campaign",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bonus> bonuses;

    @OneToMany(targetEntity = Gallery.class, mappedBy = "campaign",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Gallery> pictures;

    @JsonIgnore
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsersRatings> usersRatings = new HashSet<>();

    public void updateBonuses(Collection<Bonus> newBonuses) {
        bonuses.clear();
        bonuses.addAll(newBonuses);
    }

    public void updateTags(Collection<Tag> newTags) {
        tags.clear();
        tags.addAll(newTags);
    }

    public void updatePictures(Collection<Gallery> newPictures) {
        pictures.clear();
        pictures.addAll(newPictures);
    }

}
