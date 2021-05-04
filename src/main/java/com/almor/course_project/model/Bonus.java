package com.almor.course_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bonuses")
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bonus_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    private String name;

    private int sum;

    private String description;

    private String pictureLink;

}
