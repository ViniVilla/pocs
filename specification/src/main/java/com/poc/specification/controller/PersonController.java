package com.poc.specification.controller;

import com.poc.specification.dto.PersonDto;
import com.poc.specification.entity.Person;
import com.poc.specification.mapper.PersonMapper;
import com.poc.specification.repository.PersonRepository;
import com.poc.specification.specificationArgResolver.PersonSpecificationArgResolver;
import com.poc.specification.specificationManual.PersonSpecificationManualBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;

    @GetMapping("/specificationArgResolver")
    @ResponseStatus(OK)
    public List<PersonDto> getWithSpecificationArgResolver(final PersonSpecificationArgResolver personSpecificationArgResolver){
        return personMapper.entitiesToDtos(personRepository.findAll(personSpecificationArgResolver));
    }


    @GetMapping("/specificationManual")
    @ResponseStatus(OK)
    public List<PersonDto> search(@RequestParam(value = "search") String search) {
        PersonSpecificationManualBuilder builder = new PersonSpecificationManualBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?)(;or)?,");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            System.out.println("\n\n\n");
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
            System.out.println(matcher.group(4));
            System.out.println("\n\n\n");
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), (matcher.group(4) != null));
        }

        Specification<Person> spec = builder.build();
        return personMapper.entitiesToDtos(personRepository.findAll(spec));
    }

}
