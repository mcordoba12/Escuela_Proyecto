package org.example.introspringboot.controller;

import org.example.introspringboot.entity.Student;
import org.example.introspringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String student(Model model) {

        var students = studentService.getAllStudents();

        model.addAttribute("greeting", "Hola mundo");
        model.addAttribute("students", students);
        model.addAttribute("student", new Student());
        return "student";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute Student student) {
        //Almacenar el estudiante
        studentService.createStudent(student);
        return "redirect:/student";
    }

    // Método para mostrar los detalles de un estudiante
    @GetMapping("/{id}")
    public String studentDetail(@PathVariable long id, Model model) {
        Student student = studentService.getAllStudents().stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);

        if (student != null) {
            model.addAttribute("student", student);
            model.addAttribute("enrollments", student.getEnrollments());  // Lista de materias (enrollments)
        }
        return "studentDetail";  // Vista que muestra los detalles del estudiante
    }


}
