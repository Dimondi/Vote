package com.example.demo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    private Long id;
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id,String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
