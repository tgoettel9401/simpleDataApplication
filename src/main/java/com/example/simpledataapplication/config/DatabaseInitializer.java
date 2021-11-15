package com.example.simpledataapplication.config;

import com.example.simpledataapplication.model.Student;
import com.example.simpledataapplication.repository.StudentRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    private final StudentRepository studentRepository;

    public DatabaseInitializer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        initializeStudents();
    }

    private void initializeStudents() {
        studentRepository.save(createStudent("Student 1", "Lastname 1", LocalDate.of(1990, 1, 1)));
        studentRepository.save(createStudent("Student 2", "Lastname 2", LocalDate.of(1990, 1, 1)));
        studentRepository.save(createStudent("Student 3", "Lastname 3", LocalDate.of(1990, 1, 1)));
        studentRepository.save(createStudent("Student 4", "Lastname 4", LocalDate.of(1990, 1, 1)));
    }

    private Student createStudent(String firstName, String lastName, LocalDate birthday) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setBirthday(birthday);
        return student;
    }

}
