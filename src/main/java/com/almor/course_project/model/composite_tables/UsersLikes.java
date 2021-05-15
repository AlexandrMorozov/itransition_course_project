package com.almor.course_project.model.composite_tables;

import com.almor.course_project.model.Comment;
import com.almor.course_project.model.User;
import com.almor.course_project.model.composite_tables_keys.UsersLikesKey;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_likes")
public class UsersLikes {

    @EmbeddedId
    private UsersLikesKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("commentId")
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private boolean isLike;

}
