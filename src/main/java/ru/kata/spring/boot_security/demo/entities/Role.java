package ru.kata.spring.boot_security.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }
}