package org.example.introspringboot.service;

import org.example.introspringboot.dto.CourseDTO;
import org.example.introspringboot.dto.CourseWithStudentCountDTO;
import org.example.introspringboot.dto.CreateCourseDTO;
import org.example.introspringboot.dto.StudentDTO;
import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    List<CourseDTO> listCourseOfStudent(long studentId);

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(long id);

    void deleteCourse(long courseId);

    Course createCourse(CreateCourseDTO createCourseDTO);

    List<CourseWithStudentCountDTO> getCoursesWithStudentCount();

    Page<Course> searchCoursesByName(String name, int page, int size);




}
