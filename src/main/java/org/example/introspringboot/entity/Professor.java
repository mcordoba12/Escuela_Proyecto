package org.example.introspringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(mappedBy = "professor") //Nombre de la propiedad en la otra clase
    @JsonIgnore
    private List<Course> cursos;

    public Professor(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //ToDo: Hacer Getters y Setters


    public List<Course> getCursos() {
        return cursos;
    }

    public void setCursos(List<Course> cursos) {
        this.cursos = cursos;
    }
}
