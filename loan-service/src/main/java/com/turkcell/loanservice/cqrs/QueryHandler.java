package com.turkcell.loanservice.cqrs;

import com.turkcell.loanservice.cqrs.Query;

public interface QueryHandler <Q extends Query<R>,R>{
    R handle(Q query);
}
