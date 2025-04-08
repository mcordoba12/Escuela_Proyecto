package org.example.introspringboot.integration;

import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Professor;
import org.example.introspringboot.entity.Student;
import org.example.introspringboot.repository.CourseRepository;
import org.example.introspringboot.repository.ProfessorRepository;
import org.example.introspringboot.service.CourseService;
import org.example.introspringboot.service.EnrrollmentService;
import org.example.introspringboot.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceIntegrationTest {

    @Autowired
    private StudentService studentService;


    @Autowired
    private ProfessorRepository professorRepository;

    private Professor professor;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrrollmentService enrrollmentService;

    @BeforeEach
    void setup() {
        professor = new Professor();
        professor.setName("Alice Andrew");
        professor = professorRepository.save(professor);
    }

    @Test
    void getEnrolledStudents_WhenCourseHasStudents_ShouldReturnStudentList(){

        var course = new Course();
        course.setName("Desarrollo de Software");
        course.setProfessor(professor);
        courseService.createCourse(course);


        var student1 = new Student();
        student1.setName("Juan Pérez");
        student1.setCode("A00231234");
        student1.setProgram("Ingeniería de Sistemas");
        studentService.createStudent(student1);

        var student2 = new Student();
        student2.setName("Ana Gómez");
        student2.setCode("A0031235");
        student2.setProgram("Matemáticas");
        studentService.createStudent(student2);

        var student3 = new Student();
        student3.setName("Carlos López");
        student3.setCode("A0231236");
        student3.setProgram("Física");
        studentService.createStudent(student3);


        enrrollmentService.enrollStudent(student1.getId(), course.getId());
        enrrollmentService.enrollStudent(student2.getId(), course.getId());
        enrrollmentService.enrollStudent(student3.getId(), course.getId());

        List <Student> studentList = studentService.getStudentByCourses(course);

        assertEquals(3, studentList.size());
    }



}
