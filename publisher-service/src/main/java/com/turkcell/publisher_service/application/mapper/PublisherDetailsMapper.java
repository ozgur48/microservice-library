package com.turkcell.publisher_service.application.mapper;

import com.turkcell.publisher_service.application.dto.PublisherDetails;
import com.turkcell.publisher_service.domain.model.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherDetailsMapper {
    public PublisherDetails toResponse(Publisher publisher){
        return new PublisherDetails(
                publisher.name().value(),
                publisher.address().value()
        );
    }
}
