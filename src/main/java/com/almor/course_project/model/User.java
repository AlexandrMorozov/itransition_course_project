package com.almor.course_project.model;

import com.almor.course_project.model.composite_tables.UsersRatings;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String password;

    private String email;

    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>(); //explain-explain

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_bonuses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bonus_id"))
    private Set<Bonus> bonuses;

    @JsonIgnore
    @OneToMany(targetEntity = Campaign.class, mappedBy = "user",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Campaign> campaigns;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsersRatings> usersRatings = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean isOwnsRole(Role role) {
        return roles.contains(role);
    }
}
