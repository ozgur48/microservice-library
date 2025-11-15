package com.turkcell.memberservice.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdatedMemberResponse(String name,
                                    String email,
                                    String phone,
                                    String address) {
}
