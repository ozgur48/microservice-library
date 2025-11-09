package com.turkcell.publisher_service.application.command;

import com.turkcell.publisher_service.application.dto.CreatedPublisherResponse;
import com.turkcell.publisher_service.application.mapper.CreatePublisherMapper;
import com.turkcell.publisher_service.cqrs.CommandHandler;
import com.turkcell.publisher_service.domain.model.Publisher;
import com.turkcell.publisher_service.domain.repository.PublisherRepository;
import org.springframework.stereotype.Component;

@Component
public class CreatePublisherCommandHandler implements CommandHandler<CreatePublisherCommand, CreatedPublisherResponse> {

    private final PublisherRepository publisherRepository;
    private final CreatePublisherMapper createPublisherMapper;

    public CreatePublisherCommandHandler(PublisherRepository publisherRepository, CreatePublisherMapper createPublisherMapper) {
        this.publisherRepository = publisherRepository;
        this.createPublisherMapper = createPublisherMapper;
    }

    @Override
    public CreatedPublisherResponse handle(CreatePublisherCommand command) {
        Publisher publisher = createPublisherMapper.toDomain(command);
        publisher = publisherRepository.save(publisher);
        return createPublisherMapper.toResponse(publisher);
    }
}
