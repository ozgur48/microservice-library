package com.turkcell.publisher_service.infrastructure;


import com.turkcell.publisher_service.domain.model.Publisher;
import com.turkcell.publisher_service.domain.model.PublisherId;
import com.turkcell.publisher_service.domain.repository.PublisherRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class PublisherRepositoryAdapter implements PublisherRepository {
    private final JpaPublisherRepository jpaPublisherRepository;
    private final PublisherEntityMapper publisherEntityMapper;

    public PublisherRepositoryAdapter(JpaPublisherRepository jpaPublisherRepository, PublisherEntityMapper publisherEntityMapper) {
        this.jpaPublisherRepository = jpaPublisherRepository;
        this.publisherEntityMapper = publisherEntityMapper;
    }

    @Override
    public Publisher save(Publisher publisher) {
        JpaPublisherEntity entity = publisherEntityMapper.toEntity(publisher);
        entity = jpaPublisherRepository.save(entity);
        return publisherEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Publisher> findById(PublisherId publisherId) {
        return jpaPublisherRepository.findById(publisherId.value()).map(publisherEntityMapper::toDomain);
    }

    @Override
    public List<Publisher> findAll() {
        return jpaPublisherRepository.findAll()
                .stream()
                .map(publisherEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Publisher> findAllPaged(Integer pageIndex, Integer pageSize) {
        return jpaPublisherRepository.findAll(PageRequest.of(pageIndex, pageSize))
                .stream()
                .map(publisherEntityMapper::toDomain)
                .toList();
    }


    @Override
    public void delete(PublisherId publisherId) {
        jpaPublisherRepository.deleteById(publisherId.value());
    }
}
