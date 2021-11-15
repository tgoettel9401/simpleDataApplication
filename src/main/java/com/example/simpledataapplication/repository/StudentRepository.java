package com.example.simpledataapplication.repository;

import com.example.simpledataapplication.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}