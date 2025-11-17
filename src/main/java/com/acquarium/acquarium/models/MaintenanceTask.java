package com.acquarium.acquarium.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "maintenance_tasks")
public class MaintenanceTask {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "aquarium_id", nullable = false)
    private Long aquariumId;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "frequency")
    private String frequency; // "daily", "weekly", "monthly", "custom"
    
    @Column(name = "priority")
    private String priority; // "low", "medium", "high"
    
    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted = false;
    
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "notes")
    private String notes;
    
    public MaintenanceTask() {}

    public MaintenanceTask(Long id, Long aquariumId, String title, String description, 
                          String frequency, String priority, Boolean isCompleted, 
                          LocalDateTime dueDate, LocalDateTime completedAt, 
                          LocalDateTime createdAt, String notes) {
        this.id = id;
        this.aquariumId = aquariumId;
        this.title = title;
        this.description = description;
        this.frequency = frequency;
        this.priority = priority;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
        this.notes = notes;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAquariumId() {
        return aquariumId;
    }
    public void setAquariumId(Long aquariumId) {
        this.aquariumId = aquariumId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public Boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    // Metodi helper
    public boolean isOverdue() {
        return !isCompleted && dueDate != null && dueDate.isBefore(LocalDateTime.now());
    }
    
    public String getStatus() {
        if (isCompleted) {
            return "completed";
        } else if (isOverdue()) {
            return "overdue";
        } else {
            return "pending";
        }
    }
}
