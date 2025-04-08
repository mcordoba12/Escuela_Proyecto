package org.example.introspringboot.service.impl;

import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Enrollment;
import org.example.introspringboot.entity.Student;
import org.example.introspringboot.repository.EnrrollmentRepository;
import org.example.introspringboot.repository.StudentRepository;
import org.example.introspringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrrollmentRepository enrrollmentRepository;


    @Value("${app.pagination.size}")
    private int pageSize;

    @Override
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();

    }

    @Override
    public List<Student> getByProgram(String program) {
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


}
