package org.example.introspringboot.service.impl;


import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Enrollment;
import org.example.introspringboot.entity.Student;
import org.example.introspringboot.repository.CourseRepository;
import org.example.introspringboot.repository.EnrrollmentRepository;
import org.example.introspringboot.repository.StudentRepository;
import org.example.introspringboot.service.EnrrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EnrrollmentServiceImpl implements EnrrollmentService {


    @Autowired
    private EnrrollmentRepository enrrollmentRepository;


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    @Transactional
    public void enrollStudent(long studentId, long courseId) {

        if(enrrollmentRepository.findByStudentIdAndCourseId(studentId, courseId) != null){
            throw new RuntimeException("Student already enrolled in this course");
        } else {

            var course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
            course.setId(courseId);

            var student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

            var enrrollment = new Enrollment();
            enrrollment.setCourse(course);
            enrrollment.setStudent(student);

            enrrollmentRepository.save(enrrollment);

        /*if (true){
            throw new RuntimeException("Error a proposito");
        }*/
        }

    }






}



