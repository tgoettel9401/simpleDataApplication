package com.example.simpledataapplication.controller;

import com.example.simpledataapplication.model.Student;
import com.example.simpledataapplication.model.Teacher;
import com.example.simpledataapplication.repository.TeacherRepository;
import com.example.simpledataapplication.util.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teachers";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Student student = new Student();
        model.addAttribute("teacher", student);
        return "teacherDetails";
    }

    @GetMapping("/edit/{id}")
    public String update(Model model, @PathVariable("id") Long teacherId) throws NotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundException("Teacher", teacherId));
        model.addAttribute("teacher", teacher);
        return "teacherDetails";
    }

    @PostMapping("save")
    public RedirectView save(@ModelAttribute Teacher teacher) {
        teacherRepository.save(teacher);
        return new RedirectView("/teachers");
    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") Long teacherId) {
        teacherRepository.deleteById(teacherId);
        return new RedirectView("/teachers");
    }

}
