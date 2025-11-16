package com.turkcell.loanservice.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;
import java.util.UUID;

public record MemberId(UUID value) {
    public MemberId{
        Objects.requireNonNull(value, "MemberId can not be null");
    }
}
