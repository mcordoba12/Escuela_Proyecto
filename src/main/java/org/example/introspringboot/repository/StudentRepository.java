package org.example.introspringboot.repository;

import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByProgram(String program);

    Page<Student>findAll(Pageable pageable);


    List<Student> findByEnrollmentsCourseId(Long courseId);
}

