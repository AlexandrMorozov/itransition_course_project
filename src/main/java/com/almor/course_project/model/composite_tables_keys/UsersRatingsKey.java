package com.almor.course_project.model.composite_tables_keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UsersRatingsKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "campaign_id")
    private Long campaignId;

}
