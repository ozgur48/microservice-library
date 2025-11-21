package com.turkcell.reservationservice.application.command;

import com.turkcell.reservationservice.application.dto.CreatedReservationResponse;
import com.turkcell.reservationservice.cqrs.CommandHandler;

public class CreateReservationCommandHandler implements CommandHandler<CreateReservationCommand, CreatedReservationResponse> {



    @Override
    public CreatedReservationResponse handle(CreateReservationCommand command) {
        return null;
    }
}
