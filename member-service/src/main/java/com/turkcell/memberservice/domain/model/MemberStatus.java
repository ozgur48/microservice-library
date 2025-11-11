package com.turkcell.memberservice.domain.model;

public enum MemberStatus {
    ACTIVE,     // Kullanıcı aktif, kitap ödünç alabilir
    INACTIVE,   // Hesap dondurulmuş veya pasif
    BANNED      // Yasaklı, kitap alamaz
}
