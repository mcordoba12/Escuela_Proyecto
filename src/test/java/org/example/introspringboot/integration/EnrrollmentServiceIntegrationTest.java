package org.example.introspringboot.integration;

import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Professor;
import org.example.introspringboot.entity.Student;
import org.example.introspringboot.repository.CourseRepository;
import org.example.introspringboot.repository.EnrrollmentRepository;
import org.example.introspringboot.repository.ProfessorRepository;
import org.example.introspringboot.service.CourseService;
import org.example.introspringboot.service.EnrrollmentService;
import org.example.introspringboot.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EnrrollmentServiceIntegrationTest {

    /*
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    private Professor professor;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrrollmentService enrrollmentService;
    @Autowired
    private EnrrollmentRepository enrrollmentRepository;


    @BeforeEach
    void setup() {
        professor = new Professor();
        professor.setName("Alice Andrew");
        professor = professorRepository.save(professor);
    }

    @Test
    void enrollStudent_WhenStudentAndCourseExist_ShouldSaveEnrollment(){

        var course = new Course();
        course.setName("Desarrollo de Software");
        course.setProfessor(professor);
        courseService.createCourse(course);

        var student = new Student();
        student.setName("Juan Pérez");
        student.setCode("A00231234");
        student.setProgram("Ingeniería de Sistemas");
        studentService.createStudent(student);


        enrrollmentService.enrollStudent(student.getId(), course.getId());

        var enrollment = enrrollmentRepository.findByStudentIdAndCourseId(student.getId(), course.getId());

        assertNotNull(enrollment, "La inscripción debe existir en la base de datos");
        assertEquals(student.getId(), enrollment.getStudent().getId(), "El estudiante debe ser el correcto");
        assertEquals(course.getId(), enrollment.getCourse().getId(), "El curso debe ser el correcto");


    }

    @Test
    void enrollStudent_WhenStudentAlreadyEnrolled_ShouldNotDuplicateEnrollment() {
        // Arrange: Crear un curso
        var course = new Course();
        course.setName("Desarrollo de Software");
        course.setProfessor(professor);
        courseService.createCourse(course);  // Método para crear un curso

        // Arrange: Crear un estudiante
        var student = new Student();
        student.setName("Juan Pérez");
        student.setCode("A00231234");
        student.setProgram("Ingeniería de Sistemas");
        studentService.createStudent(student);  // Método para crear un estudiante

        // Act: Inscribir al estudiante en el curso (primer intento)
        enrrollmentService.enrollStudent(student.getId(), course.getId());

        // Assert: Verificar que la inscripción se ha guardado correctamente
        var enrollment = enrrollmentRepository.findByStudentIdAndCourseId(student.getId(), course.getId());
        assertNotNull(enrollment, "La inscripción debería existir");

        // Act: Intentar inscribir al estudiante nuevamente en el mismo curso
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            enrrollmentService.enrollStudent(student.getId(), course.getId());
        });

        // Assert: Verificar que la excepción correcta es lanzada con el mensaje esperado
        assertEquals("Student already enrolled in this course", exception.getMessage());

        // Assert: Verificar que solo hay una inscripción en la base de datos
        long count = enrrollmentRepository.count();  // Contar las inscripciones
        assertEquals(1, count, "Solo debería haber una inscripción en el curso");
    }

    @Test
    void deleteCourse_WhenCourseHasEnrollments_ShouldCascadeDeleteEnrollments() {
        // Arrange: Crear un curso
        var course = new Course();
        course.setName("Desarrollo de Software");
        course.setProfessor(professor);
        courseService.createCourse(course);  // Crear el curso

        // Arrange: Crear un estudiante
        var student = new Student();
        student.setName("Juan Pérez");
        student.setCode("A00231234");
        student.setProgram("Ingeniería de Sistemas");
        studentService.createStudent(student);  // Crear el estudiante

        enrrollmentService.enrollStudent(student.getId(), course.getId());

        // Assert: Verificar que la inscripción existe
        var enrollment = enrrollmentRepository.findByStudentIdAndCourseId(student.getId(), course.getId());
        assertNotNull(enrollment, "La inscripción debería existir");

        // Act: Eliminar el curso
        courseService.deleteCourse(course.getId());

        // Assert: Verificar que el curso ha sido eliminado
        assertFalse(courseRepository.existsById(course.getId()), "El curso debería haber sido eliminado");

        // Assert: Verificar que las inscripciones asociadas también han sido eliminadas
        var deletedEnrollment = enrrollmentRepository.findByStudentIdAndCourseId(student.getId(), course.getId());
        assertNull(deletedEnrollment, "La inscripción asociada al curso eliminado debería haber sido eliminada");
    }

     */




}
