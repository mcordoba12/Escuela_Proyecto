package org.example.introspringboot.dto;

public class EnrollmentDTO {

    private Long studentId;
    private Long courseId;

    // Getters y setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
