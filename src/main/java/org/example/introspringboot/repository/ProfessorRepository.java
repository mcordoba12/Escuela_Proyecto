package org.example.introspringboot.repository;

import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Professor;
import org.example.introspringboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.processing.Processor;
import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {



}

