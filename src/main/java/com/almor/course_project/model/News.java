package com.almor.course_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    private String header;

    private String text;

    @Temporal(TemporalType.DATE)
    private Date dateOfPost;

    //add picture maybe?????
}
