package org.example.introspringboot.repository;

import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {


    Optional <Course> findByName(String name);
}

