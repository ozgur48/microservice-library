package com.turkcell.publisher_service.application.command;

import com.turkcell.publisher_service.application.dto.DeletedPublisherResponse;
import com.turkcell.publisher_service.cqrs.CommandHandler;
import com.turkcell.publisher_service.domain.exception.PublisherNotFoundException;
import com.turkcell.publisher_service.domain.model.Publisher;
import com.turkcell.publisher_service.domain.model.PublisherId;
import com.turkcell.publisher_service.domain.repository.PublisherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeletePublisherCommandHandler implements CommandHandler<DeletePublisherCommand, DeletedPublisherResponse> {
    private final PublisherRepository publisherRepository;

    public DeletePublisherCommandHandler(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }


    @Override
    public DeletedPublisherResponse handle(DeletePublisherCommand command) {
        PublisherId publisherId = new PublisherId(command.id());

        publisherRepository.findById(publisherId).
                orElseThrow(()-> new PublisherNotFoundException(command.id()));

        publisherRepository.delete(publisherId);
        return new DeletedPublisherResponse(command.id());

    }
}
