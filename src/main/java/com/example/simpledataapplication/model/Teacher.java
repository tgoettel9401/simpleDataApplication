package com.example.simpledataapplication.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany
    private List<Course> courses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
