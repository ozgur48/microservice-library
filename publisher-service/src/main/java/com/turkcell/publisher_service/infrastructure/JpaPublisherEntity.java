package com.turkcell.publisher_service.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name="publishers")
public class JpaPublisherEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    @Size(min=1, max = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    public JpaPublisherEntity(UUID id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public JpaPublisherEntity(){}

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String address() {
        return address;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
