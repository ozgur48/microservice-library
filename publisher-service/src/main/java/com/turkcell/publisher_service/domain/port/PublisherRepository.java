package com.turkcell.publisher_service.domain.port;

import com.turkcell.publisher_service.domain.model.Publisher;
import com.turkcell.publisher_service.domain.model.PublisherId;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository {
    Optional<Publisher> findById(PublisherId id);
    List<Publisher> findAll();
    List<Publisher> findAllPaged(Integer pageIndex, Integer pageSize);
    Publisher save(Publisher publisher);
    void delete(PublisherId id);
}
