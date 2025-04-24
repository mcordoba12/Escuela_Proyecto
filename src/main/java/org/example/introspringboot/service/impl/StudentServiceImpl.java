package org.example.introspringboot.service.impl;

import org.example.introspringboot.dto.CourseDTO;
import org.example.introspringboot.dto.StudentDTO;
import org.example.introspringboot.dto.UpdateStudentDTO;
import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Enrollment;
import org.example.introspringboot.entity.Student;
import org.example.introspringboot.mapper.StudentMapper;
import org.example.introspringboot.repository.EnrrollmentRepository;
import org.example.introspringboot.repository.StudentRepository;
import org.example.introspringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrrollmentRepository enrrollmentRepository;

    @Autowired
    private StudentMapper studentMapper;


    @Value("${app.pagination.size}")
    private int pageSize;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        // Convertir el DTO a entidad Student
        Student student = studentMapper.toEntity(studentDTO);

        Student savedStudent = studentRepository.save(student);

        return studentMapper.toDTO(savedStudent);
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();

    }

    @Override
    public List<Student> getStudentsByProgram(String program) {
        return studentRepository.findByProgram(program);
    }

    @Override
    public Page<Student> findAll(int page){
        Pageable pageable = PageRequest.of(page,pageSize);
        return studentRepository.findAll(pageable);

    }

    @Override
    public List<Student> getStudentByCourses(Course course) {
        List <Enrollment> enrollments = enrrollmentRepository.findByCourse(course);
        return enrollments.stream().map(Enrollment::getStudent).toList();
    }


    @Override
    public List<CourseDTO> getCoursesForStudent(Long studentId) {
        List<Enrollment> enrollments = enrrollmentRepository.findByStudentId(studentId);

        // Mapear las matrículas a CourseDTO
        return enrollments.stream()
                .map(enrollment -> {

                    Course course = enrollment.getCourse();
                    CourseDTO courseDTO = new CourseDTO();
                    courseDTO.setId(course.getId());
                    courseDTO.setName(course.getName());

                    if (course.getProfessor() != null) {
                        courseDTO.setProfessorId(course.getProfessor().getId());
                    } else {
                        courseDTO.setProfessorId(null); // Si no hay profesor asignado
                    }

                    return courseDTO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public Student updateStudent(Long studentId, UpdateStudentDTO updateStudentDTO) {
        // Buscar el estudiante por su ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (updateStudentDTO.getStudentName() != null) {
            student.setName(updateStudentDTO.getStudentName());
        }

        if (updateStudentDTO.getStudentProgram() != null) {
            student.setProgram(updateStudentDTO.getStudentProgram());
        }

        return studentRepository.save(student);
    }


    @Override
    public Page<Student> getStudentsPagedAndSorted(int page, int size) {
        // Usamos PageRequest para la paginación y Sort para ordenar por nombre
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("name")));
        return studentRepository.findAll(pageRequest);
    }

}
