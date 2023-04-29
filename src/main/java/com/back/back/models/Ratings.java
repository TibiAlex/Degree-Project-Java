package com.back.back.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Ratings {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUser user;

    private float rating;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="course_id")
    private Course course;

    public Ratings(AppUser user, float rating, Course course) {
        this.user = user;
        this.rating = rating;
        this.course = course;
    }
}
