package com.example.simpledataapplication.repository;

import com.example.simpledataapplication.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}