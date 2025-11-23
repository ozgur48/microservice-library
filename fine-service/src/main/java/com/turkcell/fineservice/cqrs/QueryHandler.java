package com.turkcell.fineservice.cqrs;

public interface QueryHandler <Q extends Query<R>,R>{
    R handle(Q query);
}
