package com.turkcell.book_service.domain.model;

import com.turkcell.book_service.domain.exception.BookOutOfStockException;

import java.util.Objects;

public record AvailableCopies(Integer copy) {
        public AvailableCopies{
            Objects.requireNonNull(copy, "Available copy null olamaz");
            if(copy < 0)
                throw new IllegalArgumentException("Avaliable copy negatif olamaz");
        }
        public AvailableCopies decrement(){
            if (this.copy <= 0){
                throw new BookOutOfStockException(null, this.copy);
            }
            return  new AvailableCopies(this.copy -1);
        }

}
