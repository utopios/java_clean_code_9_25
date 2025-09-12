package com.example.correctionapp.dao;

import com.example.correctionapp.entity.Task;

public class TaskDAO extends GenericDAO<Task, Integer> {
    private int currentId = 1;

    @Override
    protected Integer generateId() {
        return currentId++;
    }

    @Override
    protected Integer getId(Task task) {
        return task.getId();
    }

    @Override
    protected void setId(Task task, Integer id) {
        task.setId(id);
    }
}
