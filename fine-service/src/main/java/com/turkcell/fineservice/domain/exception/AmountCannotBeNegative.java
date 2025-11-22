package com.turkcell.fineservice.domain.exception;

import java.math.BigDecimal;

public class AmountCannotBeNegative extends IllegalArgumentException {
    public AmountCannotBeNegative(BigDecimal currentAmount) {
        super(String.format(
                "Amount can not be negative",
                currentAmount
        ));
    }
}
