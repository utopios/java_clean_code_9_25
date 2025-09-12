package com.example.correctionapp.entity;

import com.example.correctionapp.exception.InvalidTaskStatusTransitionException;
import com.example.correctionapp.exception.TaskAssignmentException;

import java.util.Objects;

public class Task {
    private int id;
    private String name;
    private String description;
    private TaskStatus status;
    private Project project;
    private Employee assignedEmployee;

    public Task() {
        this.status = TaskStatus.TODO; // Statut par défaut
    }

    public Task(String name, String description) {
        this();
        setName(name);
        setDescription(description);
    }

    // Logique métier enrichie
    public void assignTo(Employee employee) {
        validateAssignment(employee);

        // Retirer de l'ancien employé si nécessaire
        if (this.assignedEmployee != null) {
            this.assignedEmployee.unassignTask(this);
        }

        this.assignedEmployee = employee;
        employee.assignTask(this);
    }

    public void updateStatus(TaskStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new InvalidTaskStatusTransitionException(
                    String.format("Impossible de passer de %s à %s", this.status, newStatus)
            );
        }

        TaskStatus oldStatus = this.status;
        this.status = newStatus;

        // Notifier le changement
        notifyStatusChange(oldStatus, newStatus);
    }

    public boolean canBeAssignedTo(Employee employee) {
        if (employee == null) return false;
        if (this.status == TaskStatus.DONE) return false;
        if (!employee.canTakeMoreTasks()) return false;

        // Vérifier que l'employé fait partie du projet
        return project != null && project.hasEmployee(employee);
    }

    private void validateAssignment(Employee employee) {
        if (!canBeAssignedTo(employee)) {
            throw new TaskAssignmentException("Impossible d'assigner la tâche à cet employé");
        }
    }

    private void notifyStatusChange(TaskStatus oldStatus, TaskStatus newStatus) {

        if (assignedEmployee != null) {
            System.out.println("Notification: Tâche '" + name + "' de " + assignedEmployee.getName() +
                    " est passée de " + oldStatus.getDisplayName() +
                    " à " + newStatus.getDisplayName());
        }
    }

    public boolean isCompleted() {
        return status == TaskStatus.DONE;
    }

    public boolean isInProgress() {
        return status == TaskStatus.IN_PROGRESS;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la tâche ne peut pas être vide");
        }
        this.name = name.trim();
    }

    public void setDescription(String description) {
        this.description = description != null ? description.trim() : null;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public Employee getAssignedEmployee() { return assignedEmployee; }
    public void setAssignedEmployee(Employee assignedEmployee) { this.assignedEmployee = assignedEmployee; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, name='%s', status=%s, assignee=%s}",
                id, name, status.getDisplayName(),
                assignedEmployee != null ? assignedEmployee.getName() : "Non assignée");
    }
}
