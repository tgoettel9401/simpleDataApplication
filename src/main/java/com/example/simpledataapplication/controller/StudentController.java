package com.example.simpledataapplication.controller;

import com.example.simpledataapplication.model.Student;
import com.example.simpledataapplication.repository.StudentRepository;
import com.example.simpledataapplication.util.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "studentDetails";
    }

    @GetMapping("/edit/{id}")
    public String showDetails(Model model, @PathVariable("id") Long studentId) throws NotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student", studentId));
        model.addAttribute("student", student);
        return "studentDetails";
    }

    @PostMapping("save")
    public RedirectView saveCreate(@ModelAttribute Student student) {
        studentRepository.save(student);
        return new RedirectView("/students");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteStudent(RedirectAttributes attributes, @PathVariable("id") Long studentId, @RequestParam("edit") Boolean isEdit) {
        studentRepository.deleteById(studentId);
        attributes.addAttribute("edit", isEdit);
        return new RedirectView("/students");
    }
    
}
