package com.turkcell.memberservice.infrastructure;

import com.turkcell.memberservice.domain.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;


import java.util.UUID;

@Entity
@Table(name="members")

public class JpaMemberEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Size(min=7, max=15)
    private String phone;

    @Column(nullable = false, length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberShipLevel memberShipLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus memberStatus;
    public JpaMemberEntity(){}
    public JpaMemberEntity(UUID id, String name, String email,
                           String phone, String address,
                           MemberShipLevel memberShipLevel,
                           MemberStatus memberStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.memberShipLevel = memberShipLevel;
        this.memberStatus = memberStatus;
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
    public String address() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MemberShipLevel memberShipLevel() {
        return memberShipLevel;
    }

    public void setMemberShipLevel(MemberShipLevel memberShipLevel) {
        this.memberShipLevel = memberShipLevel;
    }

    public MemberStatus memberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }
}
