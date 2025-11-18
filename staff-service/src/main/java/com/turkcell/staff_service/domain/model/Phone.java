package com.turkcell.staff_service.domain.model;

public record Phone(String value) {
    public Phone{
        if(value == null || value.isEmpty())
            throw new IllegalArgumentException("Phone number cannot be empty or null");


        // sadece +, rakam, boşluk, tire ve parantez
        if (!value.matches("^\\+?[0-9]{7,15}$")) {
            throw new IllegalArgumentException("Geçersiz telefon numarası formatı");
        }
        // rakamları ayıkla
        String digits = value.replaceAll("[^0-9]", "");
        if (digits.length() < 7 || digits.length() > 15)
            throw new IllegalArgumentException("Telefon numarası 7-15 haneli olmalı");
    }
}
