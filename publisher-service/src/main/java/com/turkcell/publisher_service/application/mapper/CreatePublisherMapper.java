package com.turkcell.publisher_service.application.mapper;


import com.turkcell.publisher_service.application.command.CreatePublisherCommand;
import com.turkcell.publisher_service.application.dto.CreatedPublisherResponse;
import com.turkcell.publisher_service.domain.model.Address;
import com.turkcell.publisher_service.domain.model.Name;
import com.turkcell.publisher_service.domain.model.Publisher;
import org.springframework.stereotype.Component;

@Component
public class CreatePublisherMapper{
    public Publisher toDomain(CreatePublisherCommand command){
        return Publisher.create(
                new Name(command.name()),
                new Address(command.address())
        );
    }
    public CreatedPublisherResponse toResponse(Publisher publisher){
        return new CreatedPublisherResponse(
                publisher.id().value(),
                publisher.name().value(),
                publisher.address().value()
        );
    }
}
