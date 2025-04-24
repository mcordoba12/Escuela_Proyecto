package org.example.introspringboot.dto;

public class StudentDTO {

    private Long studentId;  // Cambiado de id a studentId
    private String studentName;  // Cambiado de name a studentName

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
