package com.example.planner.service.impl;

import com.example.planner.domain.Person;
import com.example.planner.domain.request.PersonRequest;
import com.example.planner.exception.PersonNotFoundException;
import com.example.planner.mapper.PersonMapper;
import com.example.planner.repository.PersonRepository;
import com.example.planner.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(final PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long create(final PersonRequest request) {
        log.info("M=create, request={}", request);
        final var entity = PersonMapper.requestToEntity(request);
        repository.save(entity);
        return entity.getId();
    }

    @Override
    public void delete(final Long personId) {
        log.info("M=delete, personId={}", personId);
        repository.deleteById(personId);
    }

    @Override
    public Person find(final Long personId) {
        log.info("M=find, personId={}", personId);
        return repository.findById(personId)
                .orElseThrow(PersonNotFoundException::new);
    }
}
