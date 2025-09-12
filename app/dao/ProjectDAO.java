package com.example.app.dao;

import com.example.app.entity.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    private static List<Project> projects = new ArrayList<>();
    private static int currentId = 1;

    public void save(Project project) {
        project.setId(currentId++);
        projects.add(project);
        System.out.println("Projet sauvegardé : " + project.getName());
    }

    public Project getById(int id) {
        for (Project p : projects) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void update(Project project) {
        // Mise à jour fictive
        System.out.println("Projet mis à jour : " + project.getName());
    }

    public List<Project> getAllProjects() {
        return projects;
    }
}