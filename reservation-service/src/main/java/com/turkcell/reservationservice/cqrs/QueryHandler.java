package com.turkcell.reservationservice.cqrs;

public interface QueryHandler <Q extends Query<R>, R>{
    R handle(Q query);
}
