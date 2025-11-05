package com.turkcell.author_service.infrastracture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name="authors")
public class JpaAuthorEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;


    @Column(nullable = false)
    @Size(min = 2, max = 100)
    private String name;

    @Column(nullable = false)
    @Size(min=2, max = 100)
    private String surName;

    public JpaAuthorEntity(UUID id, String name, String surName) {
        this.id = id;
        this.name = name;
        this.surName = surName;
    }
    public JpaAuthorEntity(){}

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String surName() {
        return surName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
