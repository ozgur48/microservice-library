package com.turkcell.staff_service.cqrs;

public interface CommandHandler <C extends Command<R>, R> {
    R handle(C command);
}
