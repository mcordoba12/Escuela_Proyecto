package org.example.introspringboot.controller;

import org.example.introspringboot.entity.Course;
import org.example.introspringboot.entity.Professor;
import org.example.introspringboot.service.CourseService;
import org.example.introspringboot.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public String course(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("professors", professorService.getAllProfessors());
        model.addAttribute("courses", courseService.getAllCourses());  // Obtener todas las materias
        return "course";
    }

    @PostMapping
    public String createCourse(@ModelAttribute Course course) {
        courseService.createCourse(course);
        return "redirect:/course";
    }


    // Método para mostrar los detalles de una materia
    @GetMapping("/{id}")
    public String courseDetail(@PathVariable long id, Model model) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            model.addAttribute("course", course);
            model.addAttribute("students", course.getEnrollments());
            model.addAttribute("professor", course.getProfessor());
        } else {
            // Maneja el caso en que no se encuentra el curso
            return "courseNotFound";  // Aquí puedes redirigir a una página de error o página de curso no encontrado
        }
        return "courseDetail";  // Nombre de la vista que se mostrará
    }


    // Método para mostrar los detalles de un profesor
    @GetMapping("/professor/{id}")
    public String professorDetail(@PathVariable long id, Model model) {
        Professor professor = professorService.getProfessorById(id);
        if (professor != null) {
            model.addAttribute("professor", professor);
            model.addAttribute("courses", professor.getCursos());  // Cursos que el profesor tiene a su cargo
        }
        return "professorDetail";  // Vista que muestra los detalles del profesor
    }
}
