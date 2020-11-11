package com.example.planner.mapper;

import com.example.planner.domain.Person;
import com.example.planner.domain.request.PersonRequest;

public interface PersonMapper {

    static Person requestToEntity(final PersonRequest request){
        return Person.builder()
                    .name(request.getName())
                    .birthDate(request.getBirthDate())
                    .build();
    }

}
