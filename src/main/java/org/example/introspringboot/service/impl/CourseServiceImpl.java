package org.example.introspringboot.service.impl;

;
import org.example.introspringboot.dto.CourseDTO;
import org.example.introspringboot.dto.CourseWithStudentCountDTO;
import org.example.introspringboot.dto.CreateCourseDTO;
import org.example.introspringboot.dto.StudentDTO;
import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Professor;
import org.example.introspringboot.mapper.CourseMapper;
import org.example.introspringboot.repository.CourseRepository;
import org.example.introspringboot.repository.EnrrollmentRepository;
import org.example.introspringboot.repository.ProfessorRepository;
import org.example.introspringboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrrollmentRepository enrrollmentRepository;
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        var entity = courseMapper.toEntity(courseDTO);
        if(courseRepository.findByName(courseDTO.getName()).isEmpty()){
            Course courseEntity = courseRepository.save(entity);
            return courseMapper.toDTO(courseEntity);
        }else {
            throw new RuntimeException("CourseDTO already exists");
        }
    }

    @Override
    public List<CourseDTO> listCourseOfStudent(long studentId){
       var enrrollments = enrrollmentRepository.findByStudent_Id(studentId);
       return enrrollments.stream().map(enrollment -> courseMapper.toDTO(enrollment.getCourse())).toList();

    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(entity -> courseMapper.toDTO(entity)).toList();
    }

    @Override
    public CourseDTO getCourseById(long id) {
        var entity = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("CourseDTO not found"));
        return courseMapper.toDTO(entity);

       /*
        Optional<CourseDTO> optCourse = courseRepository.findById(id);
        if(optCourse.isPresent()){
            return optCourse.get();
        }else {
            throw new RuntimeException("There is no CourseDTO with id" + id);
        }*/
    }

    @Override
    public void deleteCourse(long courseId) {
        //courseRepository.deleteById(courseId );

        //*
        if (courseRepository.existsById(courseId)){
        courseRepository.deleteById(courseId );
        } else {
            throw new RuntimeException("CourseDTO not found");
        }
        //*/

    }


    @Override
    public Course createCourse(CreateCourseDTO createCourseDTO) {
        // Obtener el profesor usando el ID del DTO
        Professor professor = professorRepository.findById(createCourseDTO.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        Course course = new Course();
        course.setName(createCourseDTO.getName());
        course.setProfessor(professor);

        return courseRepository.save(course);
    }

    @Override
    public List<CourseWithStudentCountDTO> getCoursesWithStudentCount() {
        // Obtener todos los cursos
        List<Course> courses = courseRepository.findAll();

        // Mapear los cursos a CourseWithStudentCountDTO y contar los estudiantes
        return courses.stream()
                .map(course -> {
                    CourseWithStudentCountDTO dto = new CourseWithStudentCountDTO();
                    dto.setId(course.getId());
                    dto.setName(course.getName());

                    // Obtener la cantidad de estudiantes inscritos en el curso
                    long studentCount = enrrollmentRepository.countByCourseId(course.getId());
                    dto.setStudentCount(studentCount);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<Course> searchCoursesByName(String name, int page, int size) {
        // Usamos PageRequest para paginación y Sort para ordenar por nombre
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("name")));

        // Filtro de nombre usando el operador LIKE para búsqueda parcial (case insensitive)
        return courseRepository.findByNameContainingIgnoreCase(name, pageRequest);
    }

}
