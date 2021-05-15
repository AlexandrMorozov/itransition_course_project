package com.almor.course_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bonuses")
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bonus_id")
    private Long id;

    @JsonIgnore//
    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    private String name;

    private int sum;

    private String description;


}
