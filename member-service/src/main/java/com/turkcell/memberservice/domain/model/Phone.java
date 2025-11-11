package com.turkcell.memberservice.domain.model;

public record Phone(String value) {
    public Phone{
        if(value == null || value.isEmpty())
            throw new IllegalArgumentException("Phone number cannot be empty or null");

        /*
        new PhoneNumber("+905321234567");  // ✅ geçerli
        new PhoneNumber("05321234567");    // ✅ geçerli
        new PhoneNumber("90532");          // ❌ (çok kısa)
        new PhoneNumber("90+5321234567");  // ❌ (+ başta değil)
        new PhoneNumber("0532-123-4567");  // ❌ (tire izinli değil)
        */

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
