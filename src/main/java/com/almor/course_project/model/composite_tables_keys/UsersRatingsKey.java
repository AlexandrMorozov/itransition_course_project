package com.almor.course_project.model.composite_tables_keys;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UsersRatingsKey implements Serializable {

    @Column(name = "user_id")
    private Long userid;

    @Column(name = "campaign_id")
    private Long campaignid;

}
