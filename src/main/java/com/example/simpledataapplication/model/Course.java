package com.example.simpledataapplication.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
