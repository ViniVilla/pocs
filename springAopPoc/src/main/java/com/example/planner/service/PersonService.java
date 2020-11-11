package com.example.planner.service;

import com.example.planner.domain.Person;
import com.example.planner.domain.request.PersonRequest;

public interface PersonService {

    Long create(PersonRequest request);

    void delete(Long personId);

    Person find(Long personId);

}
