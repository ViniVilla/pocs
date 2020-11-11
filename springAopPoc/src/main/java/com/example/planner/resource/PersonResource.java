package com.example.planner.resource;

import com.example.planner.aop.LogEndpoint;
import com.example.planner.domain.Person;
import com.example.planner.domain.request.PersonRequest;
import com.example.planner.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonResource {

    private final PersonService personService;

    public PersonResource(final PersonService personService) {
        this.personService = personService;
    }

    @LogEndpoint
    @GetMapping(path = "/{id}")
    public Person find(@PathVariable final Long id){
        return personService.find(id);
    }

    @PostMapping
    @LogEndpoint
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final PersonRequest request){
        personService.create(request);
    }

    @LogEndpoint
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable final Long id){
        personService.delete(id);
    }

}
