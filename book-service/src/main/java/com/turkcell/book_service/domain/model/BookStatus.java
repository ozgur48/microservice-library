package com.turkcell.book_service.domain.model;

public enum BookStatus {
    AVAILABLE,   // ödünç alınabilir
    CHECKED_OUT, // ödünçte
    RESERVED,    // rezerve edilmiş
    LOST,        // kayıp
    DAMAGED,      // hasarlı
    INACTIVE
}
