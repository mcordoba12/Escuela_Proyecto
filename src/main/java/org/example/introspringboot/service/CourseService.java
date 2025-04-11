package org.example.introspringboot.service;

import org.example.introspringboot.dto.CourseDTO;
import org.example.introspringboot.entity.Student;

import java.util.List;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    List<CourseDTO> listCourseOfStudent(long studentId);

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(long id);

    void deleteCourse(long courseId);
}
