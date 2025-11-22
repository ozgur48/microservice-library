package com.turkcell.fineservice.domain.model;
import com.turkcell.fineservice.domain.exception.AmountCannotBeNegative;

import java.math.BigDecimal;
import java.util.Objects;

public record Amount(BigDecimal value) {
    public Amount{
        Objects.requireNonNull(value, "Amount can not be null");
        if(value.compareTo(BigDecimal.ZERO) < 0){
            throw new AmountCannotBeNegative(value);
        }
    }
    public Amount add(Amount other){
        return new Amount(this.value.add(other.value));
    }
    public Amount subtract(Amount other){
        return new Amount(this.value.subtract(other.value));
    }
}