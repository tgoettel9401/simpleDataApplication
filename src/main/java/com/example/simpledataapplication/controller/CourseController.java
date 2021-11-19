package com.example.simpledataapplication.controller;

import com.example.simpledataapplication.model.Course;
import com.example.simpledataapplication.model.Teacher;
import com.example.simpledataapplication.repository.CourseRepository;
import com.example.simpledataapplication.repository.StudentRepository;
import com.example.simpledataapplication.repository.TeacherRepository;
import com.example.simpledataapplication.util.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public CourseController(CourseRepository courseRepository, TeacherRepository teacherRepository,
                            StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "courses";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        model.addAttribute("teachers", teacherRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        return "courseDetails";
    }

    @GetMapping("/edit/{id}")
    public String update(Model model, @PathVariable("id") Long courseId) throws NotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course", courseId));
        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("course", course);
        model.addAttribute("teachers", teacherRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        return "courseDetails";
    }

    @PostMapping("save")
    public RedirectView save(@ModelAttribute CourseRequest courseRequest) {
        Course course = new Course();
        course.setId(courseRequest.getId());
        course.setName(courseRequest.getName());
        course.setTeacher(teacherRepository.findById(courseRequest.getTeacherId()).orElse(null));
        course.setStudents(studentRepository.findAllById(courseRequest.getStudentIds()));
        courseRepository.save(course);
        return new RedirectView("/courses");
    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") Long courseId) {
        courseRepository.deleteById(courseId);
        return new RedirectView("/courses");
    }

}
