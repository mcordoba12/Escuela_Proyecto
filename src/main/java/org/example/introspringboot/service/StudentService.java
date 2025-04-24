package org.example.introspringboot.service;

import org.example.introspringboot.dto.CourseDTO;
import org.example.introspringboot.dto.StudentDTO;
import org.example.introspringboot.dto.UpdateStudentDTO;
import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);
    List<Student> getAllStudents();
    Page<Student> findAll(int page);

    List<Student> getStudentByCourses(Course course);
    List<CourseDTO> getCoursesForStudent(Long studentId);
    List<Student> getStudentsByProgram(String program);

    Student updateStudent(Long studentId, UpdateStudentDTO updateStudentDTO);
    Page<Student> getStudentsPagedAndSorted(int page, int size);

}
