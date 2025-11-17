package com.acquarium.acquarium.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private int volume;
    private String type;
    private LocalDateTime createdAt;
    private String description;
    private String imageUrl;
}
