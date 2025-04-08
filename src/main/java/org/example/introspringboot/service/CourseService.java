package org.example.introspringboot.service;

import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Student;

import java.util.List;

public interface CourseService {

    Course createCourse(Course course);

    List<Course> listCourseOfStudent(long studentId);

    List<Course> getAllCourses();

    Course getCourseById(long id);

    void deleteCourse(long courseId);
}
