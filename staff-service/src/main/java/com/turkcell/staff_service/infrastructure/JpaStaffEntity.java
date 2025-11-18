package com.turkcell.staff_service.infrastructure;

import com.turkcell.staff_service.domain.model.StaffLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name="books")
public class JpaStaffEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    @Size(max = 150)
    private String name;

    @Size(max = 150)
    private String email;

    @Column(nullable = false)
    @Size(min=7, max=15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "staff_level", nullable = false)
    private StaffLevel staffLevel;

    public JpaStaffEntity(){}


    public JpaStaffEntity(UUID id, String name, String email, String phone, StaffLevel staffLevel) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.staffLevel = staffLevel;
    }

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String phone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StaffLevel staffLevel() {
        return staffLevel;
    }

    public void setStaffLevel(StaffLevel staffLevel) {
        this.staffLevel = staffLevel;
    }
}
