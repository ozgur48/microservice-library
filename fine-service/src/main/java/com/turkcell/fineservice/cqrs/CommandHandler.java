package com.turkcell.fineservice.cqrs;

public interface CommandHandler <C extends Command<R>, R>{
    R handle(C command);
}
