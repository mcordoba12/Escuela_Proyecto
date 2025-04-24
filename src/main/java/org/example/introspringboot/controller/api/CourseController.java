package org.example.introspringboot.controller.api;

import org.example.introspringboot.dto.CourseDTO;
import org.example.introspringboot.dto.CourseWithStudentCountDTO;
import org.example.introspringboot.dto.CreateCourseDTO;
import org.example.introspringboot.dto.StudentDTO;
import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Student;
import org.example.introspringboot.service.CourseService;
import org.example.introspringboot.service.EnrrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    /*
    @GetMapping("/course")
    public ResponseEntity<?> hello() {
        return ResponseEntity.status(200).body(
                List.of("Course 1", "Course 2", "Course 3")
        );
    }*/

    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsInCourse(@PathVariable Long courseId) {
        List<StudentDTO> students = enrollmentService.getStudentsByCourse(courseId);
        return ResponseEntity.ok(students);
    }


    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody CreateCourseDTO createCourseDTO) {
        // Llamar al servicio para crear el curso
        Course createdCourse = courseService.createCourse(createCourseDTO);

        // Retornar el curso creado
        return ResponseEntity.ok(createdCourse);
    }

    @GetMapping("/withStudentCount")
    public ResponseEntity<List<CourseWithStudentCountDTO>> getCoursesWithStudentCount() {
        List<CourseWithStudentCountDTO> courses = courseService.getCoursesWithStudentCount();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Course>> searchCoursesByName(
            @RequestParam String name,
            @RequestParam int page,
            @RequestParam int size) {

        // Llamar al servicio para obtener los cursos que coincidan parcialmente con el nombre
        Page<Course> courses = courseService.searchCoursesByName(name, page, size);

        return ResponseEntity.ok(courses);
    }

}
