package org.example.introspringboot.service;

import org.example.introspringboot.dto.EnrollmentDTO;
import org.example.introspringboot.dto.StudentDTO;
import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Enrollment;
import org.example.introspringboot.entity.Student;

import java.util.List;

public interface EnrrollmentService {

    Enrollment enrollStudent(EnrollmentDTO enrollmentDTO);

    void deleteEnrollment(Long enrollmentId);
    List<StudentDTO> getStudentsByCourse(Long courseId);
}
