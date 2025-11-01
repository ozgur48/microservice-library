package com.turkcell.book_service.application.book.dto;

public enum BookStatusDto {
    AVAILABLE,   // ödünç alınabilir
    CHECKED_OUT, // ödünçte
    RESERVED,    // rezerve edilmiş
    LOST,        // kayıp
    DAMAGED,      // hasarlı
    INACTIVE
}
