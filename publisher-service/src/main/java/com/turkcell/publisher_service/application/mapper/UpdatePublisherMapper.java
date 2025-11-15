package com.turkcell.publisher_service.application.mapper;

import com.turkcell.publisher_service.application.dto.UpdatedPublisherResponse;
import com.turkcell.publisher_service.domain.model.Publisher;
import org.springframework.stereotype.Component;

@Component
public class UpdatePublisherMapper {
    public UpdatedPublisherResponse toResponse(Publisher publisher){
        return new UpdatedPublisherResponse(
                publisher.name().value(),
                publisher.address().value()
        );
    }
}
