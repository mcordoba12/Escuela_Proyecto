package org.example.introspringboot.dto;

public class UpdateStudentDTO {

    private String studentName;
    private String studentProgram;

    // Getters y Setters
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentProgram() {
        return studentProgram;
    }

    public void setStudentProgram(String studentProgram) {
        this.studentProgram = studentProgram;
    }
}
