package org.example.introspringboot.dto;

public class CreateCourseDTO {

    private String name;
    private Long professorId;  // El ID del profesor asignado al curso

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}
