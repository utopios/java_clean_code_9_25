package com.example.app.dao;

import com.example.app.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private static List<Task> tasks = new ArrayList<>();
    private static int currentId = 1;

    public void save(Task task) {
        task.setId(currentId++);
        tasks.add(task);
        System.out.println("Tâche sauvegardée : " + task.getName());
    }

    public Task getById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public void update(Task task) {
        // Mise à jour fictive
        System.out.println("Tâche mise à jour : " + task.getName());
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}
