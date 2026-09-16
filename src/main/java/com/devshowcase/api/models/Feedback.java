package com.devshowcase.api.models;

import jakarta.persistence.*;

@Entity
public class Feedback {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne 
    @JoinColumn(name = "project_id")
    private Project project;

    public Feedback() {}

    public Long getId() { return id; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}