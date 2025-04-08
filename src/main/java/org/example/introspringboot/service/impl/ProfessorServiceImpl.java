package org.example.introspringboot.service.impl;


import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Enrollment;
import org.example.introspringboot.entity.Professor;
import org.example.introspringboot.entity.Student;
import org.example.introspringboot.repository.EnrrollmentRepository;
import org.example.introspringboot.repository.ProfessorRepository;
import org.example.introspringboot.service.EnrrollmentService;
import org.example.introspringboot.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProfessorServiceImpl implements ProfessorService {


    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public Professor getProfessorById(long id) {
        return professorRepository.findById(id).orElse(null);  // Si no se encuentra, retorna null
    }



}



