package com.turkcell.staff_service.domain.model;

public class Staff {
    private final StaffId id;
    private Name name;
    private StaffLevel staffLevel;
    private Email email;
    private Phone phone;

    public Staff(StaffId id, Name name, StaffLevel staffLevel,Email email, Phone phone) {
        this.id = id;
        this.name = name;
        this.staffLevel = staffLevel;
        this.email = email;
        this.phone = phone;
    }

    public static Staff create(Name name,StaffLevel staffLevel, Email email, Phone phone){
        return new Staff(StaffId.generate(), name, staffLevel, email, phone);
    }

    public static Staff rehydrate(StaffId id, Name name,StaffLevel staffLevel, Email email, Phone phone){
        return new Staff(id, name, staffLevel, email, phone);
    }

    public void updateName(Name name){
        this.name = name;
    }

    public void updateEmail(Email newEmail){
        this.email = newEmail;
    }

    public void updatePhone(Phone newPhone){
        this.phone = newPhone;
    }


    public StaffId id() {
        return id;
    }

    public Name name() {
        return name;
    }

    public Email email() {
        return email;
    }

    public Phone phone() {
        return phone;
    }

    public StaffLevel staffLevel() {
        return staffLevel;
    }
}
