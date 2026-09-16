package com.devshowcase.api.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Technology {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "technologies")
    private Set<Project> projects = new HashSet<>();

    public Technology() {}
    
    public Technology(String name) { 
        this.name = name; 
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<Project> getProjects() { return projects; }
}