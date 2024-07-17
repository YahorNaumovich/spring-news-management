package edu.web.training.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "UserRole")
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

}