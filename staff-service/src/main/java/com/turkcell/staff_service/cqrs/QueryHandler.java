package com.turkcell.staff_service.cqrs;

public interface QueryHandler <Q extends Query<R>,R> {
    R handle(Q query);
}
