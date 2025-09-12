package com.example.correctionapp.dao;

import com.example.correctionapp.entity.Project;

public class ProjectDAO extends GenericDAO<Project, Integer> {
    private int currentId = 1;

    @Override
    protected Integer generateId() {
        return currentId++;
    }

    @Override
    protected Integer getId(Project project) {
        return project.getId();
    }

    @Override
    protected void setId(Project project, Integer id) {
        project.setId(id);
    }
}