package com.turkcell.memberservice.application.dto;

public enum MemberStatusDto {
    ACTIVE,     // Kullanıcı aktif, kitap ödünç alabilir
    INACTIVE,   // Hesap dondurulmuş veya pasif
    BANNED      // Yasaklı, kitap alamaz
}
