package com.example.simpledataapplication.config;

import com.example.simpledataapplication.model.Course;
import com.example.simpledataapplication.model.Student;
import com.example.simpledataapplication.model.Teacher;
import com.example.simpledataapplication.repository.CourseRepository;
import com.example.simpledataapplication.repository.StudentRepository;
import com.example.simpledataapplication.repository.TeacherRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public DatabaseInitializer(StudentRepository studentRepository, TeacherRepository teacherRepository,
                               CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<Student> students = initializeStudents();
        List<Teacher> teachers = initializeTeachers();
        List<Course> courses = initializeCourses(teachers, students);
    }

    private List<Student> initializeStudents() {
        List<Student> students = new ArrayList<>();
        students.add(createStudent("Student 1", "Lastname 1", LocalDate.of(1990, 1, 1)));
        students.add(createStudent("Student 2", "Lastname 2", LocalDate.of(1990, 1, 1)));
        students.add(createStudent("Student 3", "Lastname 3", LocalDate.of(1990, 1, 1)));
        students.add(createStudent("Student 4", "Lastname 4", LocalDate.of(1990, 1, 1)));
        return studentRepository.saveAll(students);
    }

    private List<Teacher> initializeTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(createTeacher("Teacher 1", "Lastname"));
        teachers.add(createTeacher("Teacher 2", "Lastname"));
        teachers.add(createTeacher("Teacher 3", "Lastname"));
        teachers.add(createTeacher("Teacher 4", "Lastname"));
        return teacherRepository.saveAll(teachers);
    }

    private List<Course> initializeCourses(List<Teacher> teachers, List<Student> students) {
        List<Course> courses = new ArrayList<>();
        courses.add(createCourse("Course 1", teachers.get(0), students));
        courses.add(createCourse("Course 2", teachers.get(1), students));
        courses.add(createCourse("Course 3", teachers.get(2), students));
        return courseRepository.saveAll(courses);
    }

    private Student createStudent(String firstName, String lastName, LocalDate birthday) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setBirthday(birthday);
        return student;
    }

    private Teacher createTeacher(String firstName, String lastName) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        return teacher;
    }

    private Course createCourse(String name, Teacher teacher, List<Student> students) {
        Course course = new Course();
        course.setName(name);
        course.setTeacher(teacher);
        course.setStudents(students);
        return course;
    }

}
