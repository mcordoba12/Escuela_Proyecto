package org.example.introspringboot.integration;

import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Enrollment;
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
public class CourseServiceIntegrationTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    private Professor professor;
    @Autowired
    private EnrrollmentService enrrollmentService;
    @Autowired
    private StudentService studentService;

    @BeforeEach
    void setup() {
        professor = new Professor();
        professor.setName("Alice Andrew");
        professor = professorRepository.save(professor);
    }

    @Test
    void createCourse_WhenValid_ReturnsSavedCourse() {
        // Arrange
        Course course = new Course();
        course.setName("Computación en Internet II");
        course.setProfessor(professor);

        // Act
        Course savedCourse = courseService.createCourse(course);

        // Assert
        assertNotNull(savedCourse.getId());
        assertEquals("Computación en Internet II", savedCourse.getName());
        assertNotNull(savedCourse.getProfessor());
        assertEquals(professor.getId(), savedCourse.getProfessor().getId());

        // Verificar que realmente está en la BD
        Course foundCourse = courseRepository.findById(savedCourse.getId()).orElse(null);
        assertNotNull(foundCourse);
        assertEquals("Computación en Internet II", foundCourse.getName());
    }

    @AfterEach
    void cleanup() {
        courseRepository.deleteAll();
        professorRepository.deleteAll();
    }



    @Test
    void saveCourse_WhenCourseAlreadyExists_ShouldThrowException(){
        // Arrange
        var courseA = new Course();
        courseA.setName("Software IV");
        courseA.setProfessor(professor);


        Course courseB = new Course();
        courseB.setName("Software IV");
        courseB.setProfessor(professor);

        assertThrows(RuntimeException.class, () -> {
            courseService.createCourse(courseA);
            courseService.createCourse(courseB);
        });
    }




}
