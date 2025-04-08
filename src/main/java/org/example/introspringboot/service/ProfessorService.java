package org.example.introspringboot.service;

import org.example.introspringboot.entity.Professor;

import java.util.List;

public interface ProfessorService {


    List<Professor> getAllProfessors();

    Professor getProfessorById(long id);
}
