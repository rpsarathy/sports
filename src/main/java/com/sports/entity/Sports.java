package com.sports.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sports")
@Data
public class Sports {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy = "sports",fetch = FetchType.LAZY)
    private Set<Players> players=new HashSet<>();

}
