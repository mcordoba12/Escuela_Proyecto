package org.example.introspringboot.service.impl;

;
import org.example.introspringboot.entity.Course;
import org.example.introspringboot.repository.CourseRepository;
import org.example.introspringboot.repository.EnrrollmentRepository;
import org.example.introspringboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrrollmentRepository enrrollmentRepository;


    @Override
    public Course createCourse(Course course) {
        if(courseRepository.findByName(course.getName()).isEmpty()){
            return courseRepository.save(course);
        }else {
            throw new RuntimeException("Course already exists");
        }
    }

    @Override
    public List<Course> listCourseOfStudent(long studentId){
       var enrrollments = enrrollmentRepository.findByStudent_Id(studentId);
       return enrrollments.stream().map(enrollment ->  enrollment.getCourse()).toList();

    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(long id) {


        return courseRepository.findById(id).orElseThrow( () -> new RuntimeException("Course not found") );
       /*
        Optional<Course> optCourse = courseRepository.findById(id);
        if(optCourse.isPresent()){
            return optCourse.get();
        }else {
            throw new RuntimeException("There is no course with id" + id);
        }*/
    }

    @Override
    public void deleteCourse(long courseId) {
        //courseRepository.deleteById(courseId );

        //*
        if (courseRepository.existsById(courseId)){
        courseRepository.deleteById(courseId );
        } else {
            throw new RuntimeException("Course not found");
        }
        //*/

    }
}
