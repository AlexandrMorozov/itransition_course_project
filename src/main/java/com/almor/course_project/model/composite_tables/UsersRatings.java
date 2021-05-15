package com.almor.course_project.model.composite_tables;

import com.almor.course_project.model.Campaign;
import com.almor.course_project.model.User;
import com.almor.course_project.model.composite_tables_keys.UsersRatingsKey;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_ratings")
public class UsersRatings {

    @EmbeddedId
    private UsersRatingsKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("campaignId")
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    private double rating;
}
