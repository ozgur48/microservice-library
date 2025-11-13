package com.turkcell.author_service.domain.model;

import java.util.Objects;

public class Author {
    private final AuthorId id;
    private Name name;
    private Surname surname;
    
    private Author(AuthorId id, Name name, Surname surname){
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
    public static Author create(Name name, Surname surname){
        return new Author(AuthorId.generate(), name, surname);
    }
    public static Author rehydrate(AuthorId id ,Name name, Surname surname){
        return new Author(id, name, surname);
    }

    public void reName(Name newName){
        Objects.requireNonNull(newName, "Yazar ismi boş olamaz");
        this.name = newName;
    }
    public void reName(Surname newSurName){
        Objects.requireNonNull(newSurName, "Yazar soyismi boş olamaz");
        this.surname = newSurName;
    }
    public void updateAuthor(Name name, Surname surname){
        this.name = name;
        this.surname = surname;
    }

    public AuthorId id() {
        return id;
    }

    public Name name() {
        return name;
    }

    public Surname surname() {
        return surname;
    }
}
