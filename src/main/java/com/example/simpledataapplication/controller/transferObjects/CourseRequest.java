package com.example.simpledataapplication.controller.transferObjects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseRequest {
    private Long id;
    private String name;
    private Long teacherId;
    private List<Long> studentIds = new ArrayList<>();
}
