package org.example.introspringboot.service.impl;


import org.example.introspringboot.dto.EnrollmentDTO;
import org.example.introspringboot.dto.StudentDTO;
import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Enrollment;
import org.example.introspringboot.entity.Student;
import org.example.introspringboot.mapper.CourseMapper;
import org.example.introspringboot.mapper.StudentMapper;
import org.example.introspringboot.repository.CourseRepository;
import org.example.introspringboot.repository.EnrrollmentRepository;
import org.example.introspringboot.repository.StudentRepository;
import org.example.introspringboot.service.EnrrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EnrrollmentServiceImpl implements EnrrollmentService {


    @Autowired
    private EnrrollmentRepository enrrollmentRepository;


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public Enrollment enrollStudent(EnrollmentDTO enrollmentDTO) {
        // Obtener el estudiante y el curso por sus respectivos ID
        Student student = studentRepository.findById(enrollmentDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(enrollmentDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrrollmentRepository.save(enrollment);
    }

    public List<StudentDTO> getStudentsByCourse(Long courseId) {
        List<Enrollment> enrollments = enrrollmentRepository.findByCourseId(courseId);

        return enrollments.stream()
                .map(enrollment -> studentMapper.toDTO(enrollment.getStudent()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEnrollment(Long enrollmentId) {
        // Verificar si la matrícula existe antes de eliminar
        Enrollment enrollment = enrrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        // Eliminar la matrícula
        enrrollmentRepository.delete(enrollment);
    }



}



