package com.sports.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "sports")
@Data
public class Sports {

    @Id
    private int id;
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy = "sports")
    private Set<Players> players;

}