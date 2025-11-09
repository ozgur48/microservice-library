package com.turkcell.publisher_service.cqrs;

public interface CommandHandler <C extends Command<R>, R>{
    R handle(C command);
}
