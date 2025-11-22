package com.turkcell.fineservice.domain.model;

import java.util.Objects;

public record Reason(String value) {
    private static final int MAX_LENGTH = 500;

    public Reason {

        Objects.requireNonNull(value, "Ceza nedeni boş olamaz (null).");

        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Ceza nedeni metni boş bırakılamaz.");
        }
        // 3. Uzunluk Kontrolü (Yeni Kural)
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("Ceza nedeni metni maksimum %d karakteri (%d) aşamaz.",
                            MAX_LENGTH, value.length())
            );
        }
    }
}
