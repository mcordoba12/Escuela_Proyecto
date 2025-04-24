package org.example.introspringboot.mapper;

import org.example.introspringboot.dto.StudentDTO;
import org.example.introspringboot.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "id", target = "studentId")
    @Mapping(source = "name", target = "studentName")
    StudentDTO toDTO(Student student);


    @Mapping(source = "studentId", target = "id")
    @Mapping(source = "studentName", target = "name")
    Student toEntity(StudentDTO studentDTO);
}
