package com.example.simpledataapplication.controller;

import com.example.simpledataapplication.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping()
    public String showAll(Model model) {
        logger.info("Showing all students");
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }


}
