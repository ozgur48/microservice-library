package com.turkcell.reservationservice.interfaces.web;

import com.turkcell.reservationservice.application.command.CreateReservationCommand;
import com.turkcell.reservationservice.application.dto.CreatedReservationResponse;
import com.turkcell.reservationservice.cqrs.CommandHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
@Validated
public class ReservationsController {
    private final CommandHandler<CreateReservationCommand, CreatedReservationResponse> createReservationCommandHandler;

    public ReservationsController(CommandHandler<CreateReservationCommand, CreatedReservationResponse> createReservationCommandHandler) {
        this.createReservationCommandHandler = createReservationCommandHandler;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedReservationResponse createReservation(@Valid @RequestBody CreateReservationCommand command){
        return createReservationCommandHandler.handle(command);
    }
}
