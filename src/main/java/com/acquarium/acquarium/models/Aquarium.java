package com.acquarium.acquarium.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "aquariums")
public class Aquarium {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "volume")
    private int volume;

    @Column(name = "type")
    private String type;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    // Costruttore vuoto richiesto da JPA
    public Aquarium() {
    }

    public Aquarium(int id, String name, int volume, String type, LocalDateTime createdAt, String description, String imageUrl){
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.type = type;
        this.createdAt = createdAt;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters e Setters
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setVolume(int volume){
        this.volume = volume;
    }
    public int getVolume(){
        return this.volume;
    }

    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public String getImageUrl(){
        return this.imageUrl;
    }
}