package com.sports.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "players")
@Data
public class Players {

    @Id
    private int id;
    @Column(name = "email", nullable = false,length = 20)
    private String email;

    @Column(name = "level", nullable = false,length = 5)
    private Integer level;

    @Column(name = "age", nullable = false,length = 25)
    private Integer age;

    @Column(name = "gender" ,length = 8)
    private String gender;

    @Enumerated(EnumType.ORDINAL)
    public String getGender() {
        return gender;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL})
    private Set<Sports> sports;

}
