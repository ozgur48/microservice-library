package com.turkcell.memberservice.domain.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record MemberId(UUID value) implements Serializable {
    public MemberId{
        Objects.requireNonNull(value, "Value for memberId cannot be null");
    }
    public static MemberId generate(){return new MemberId(UUID.randomUUID());}
}
