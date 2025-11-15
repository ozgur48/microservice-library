package com.turkcell.publisher_service.application.query;

import com.turkcell.publisher_service.application.dto.PublisherDetails;
import com.turkcell.publisher_service.application.mapper.PublisherDetailsMapper;
import com.turkcell.publisher_service.cqrs.QueryHandler;
import com.turkcell.publisher_service.domain.exception.PublisherNotFoundException;
import com.turkcell.publisher_service.domain.model.Publisher;
import com.turkcell.publisher_service.domain.model.PublisherId;
import com.turkcell.publisher_service.domain.repository.PublisherRepository;

public class GetPublisherDetailsQueryHandler implements QueryHandler<GetPublisherDetailsQuery, PublisherDetails> {
    private final PublisherRepository publisherRepository;
    private final PublisherDetailsMapper publisherDetailsMapper;

    public GetPublisherDetailsQueryHandler(PublisherRepository publisherRepository, PublisherDetailsMapper publisherDetailsMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherDetailsMapper = publisherDetailsMapper;
    }

    @Override
    public PublisherDetails handle(GetPublisherDetailsQuery query) {
        PublisherId publisherId = new PublisherId(query.id());
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(()-> new PublisherNotFoundException(query.id()));
        return publisherDetailsMapper.toResponse(publisher);
    }
}
