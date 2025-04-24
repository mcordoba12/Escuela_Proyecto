package org.example.introspringboot.controller.api;

import org.example.introspringboot.dto.CourseDTO;
import org.example.introspringboot.dto.StudentDTO;
import org.example.introspringboot.dto.UpdateStudentDTO;
import org.example.introspringboot.entity.Student;
import org.example.introspringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<StudentDTO> registerStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.ok(createdStudent);
    }


    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<CourseDTO>> getCoursesForStudent(@PathVariable Long studentId) {
        List<CourseDTO> courses = studentService.getCoursesForStudent(studentId);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long studentId,
            @RequestBody UpdateStudentDTO updateStudentDTO) {

        // Llamar al servicio para actualizar el estudiante
        Student updatedStudent = studentService.updateStudent(studentId, updateStudentDTO);

        // Retornar el estudiante actualizado
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/byProgram")
    public ResponseEntity<List<Student>> getStudentsByProgram(@RequestParam String program) {
        // Llamar al servicio para obtener los estudiantes por programa
        List<Student> students = studentService.getStudentsByProgram(program);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Student>> getStudentsPagedAndSorted(
            @RequestParam int page,
            @RequestParam int size) {

        // Obtener los estudiantes paginados y ordenados por nombre
        Page<Student> studentsPage = studentService.getStudentsPagedAndSorted(page, size);

        return ResponseEntity.ok(studentsPage);
    }

}
