package com.almor.course_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_bonuses",
            joinColumns = @JoinColumn(name = "bonus_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    private String name;

    private int sum;

    private String description;

}
