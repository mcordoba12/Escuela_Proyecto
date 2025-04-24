package org.example.introspringboot.dto;

public class CourseWithStudentCountDTO {

    private long id;
    private String name;
    private long studentCount;  // Contador de estudiantes inscritos

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(long studentCount) {
        this.studentCount = studentCount;
    }
}
