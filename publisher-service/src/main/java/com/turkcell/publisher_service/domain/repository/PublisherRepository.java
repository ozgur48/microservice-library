package com.turkcell.publisher_service.domain.repository;

import com.turkcell.publisher_service.domain.model.Publisher;
import com.turkcell.publisher_service.domain.model.PublisherId;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository {
    Publisher save(Publisher publisher);
    Optional<Publisher> findById(PublisherId publisherId);
    List<Publisher> findAll();
    List<Publisher> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(PublisherId publisherId);
}
