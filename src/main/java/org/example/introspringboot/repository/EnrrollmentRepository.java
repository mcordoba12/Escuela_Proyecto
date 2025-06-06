package org.example.introspringboot.repository;

import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Enrollment;
import org.example.introspringboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EnrrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudent_Id(long studentId);

    List<Enrollment> findByCourse(Course course);

    Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId);

    List<Enrollment> findByCourseId(Long courseId);


    List<Enrollment> findByStudentId(Long studentId);

    long countByCourseId(long id);
}

