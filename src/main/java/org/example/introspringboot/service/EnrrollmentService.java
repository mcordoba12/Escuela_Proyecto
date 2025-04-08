package org.example.introspringboot.service;

import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Enrollment;
import org.example.introspringboot.entity.Student;

import java.util.List;

public interface EnrrollmentService {

    void enrollStudent(long studentId, long courseId);


}
