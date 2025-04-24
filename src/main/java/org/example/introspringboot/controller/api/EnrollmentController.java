package org.example.introspringboot.controller.api;

import org.example.introspringboot.dto.EnrollmentDTO;
import org.example.introspringboot.entity.Enrollment;
import org.example.introspringboot.service.EnrrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrrollmentService enrollmentService;

    @PostMapping("/register")
    public ResponseEntity<Enrollment> matriculateStudent(@RequestBody EnrollmentDTO enrollmentDTO) {
        // Llamar al servicio para matricular al estudiante en el curso
        Enrollment enrollment = enrollmentService.enrollStudent(enrollmentDTO);

        // Retornar la matrícula creada
        return ResponseEntity.ok(enrollment);
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long enrollmentId) {
        // Llamar al servicio para eliminar la matrícula
        enrollmentService.deleteEnrollment(enrollmentId);

        // Retornar una respuesta 204 No Content para indicar que la eliminación fue exitosa
        return ResponseEntity.noContent().build();
    }
}
