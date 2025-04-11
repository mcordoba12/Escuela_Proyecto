package org.example.introspringboot.service.impl;

;
import org.example.introspringboot.dto.CourseDTO;
import org.example.introspringboot.entity.Course;
import org.example.introspringboot.mapper.CourseMapper;
import org.example.introspringboot.repository.CourseRepository;
import org.example.introspringboot.repository.EnrrollmentRepository;
import org.example.introspringboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrrollmentRepository enrrollmentRepository;
    @Autowired
    private CourseMapper courseMapper;


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
}
