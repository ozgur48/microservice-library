package com.turkcell.publisher_service.application.command;

import com.turkcell.publisher_service.application.dto.UpdatedPublisherResponse;
import com.turkcell.publisher_service.application.mapper.UpdatePublisherMapper;
import com.turkcell.publisher_service.cqrs.CommandHandler;
import com.turkcell.publisher_service.domain.exception.PublisherNotFoundException;
import com.turkcell.publisher_service.domain.model.Address;
import com.turkcell.publisher_service.domain.model.Name;
import com.turkcell.publisher_service.domain.model.Publisher;
import com.turkcell.publisher_service.domain.model.PublisherId;
import com.turkcell.publisher_service.domain.repository.PublisherRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdatePublisherCommandHandler implements CommandHandler<UpdatePublisherCommand, UpdatedPublisherResponse> {
    private final PublisherRepository publisherRepository;
    private final UpdatePublisherMapper updatePublisherMapper;

    public UpdatePublisherCommandHandler(PublisherRepository publisherRepository, UpdatePublisherMapper updatePublisherMapper) {
        this.publisherRepository = publisherRepository;
        this.updatePublisherMapper = updatePublisherMapper;
    }

    @Override
    public UpdatedPublisherResponse handle(UpdatePublisherCommand command) {
        Publisher publisher = publisherRepository.findById(new PublisherId(command.id()))
                .orElseThrow(()-> new PublisherNotFoundException(command.id()));

        Name name = new Name(command.name());
        Address address = new Address(command.address());

        publisher.updateName(name);
        publisher.UpdateAddress(address);

        return updatePublisherMapper.toResponse(publisher);

    }
}
