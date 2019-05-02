package com.poc.specification.config;

import com.poc.specification.entity.Address;
import com.poc.specification.entity.Person;
import com.poc.specification.repository.AddressRepository;
import com.poc.specification.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InsertIntoDatabase {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PersonRepository personRepository;

    @PostConstruct
    public void insertIntoDatabase(){
        Address address = new Address();
        address.setCity("New York");
        address.setStreet("Wood Avenue");
        address.setNumber("100");

        Address address2 = new Address();
        address2.setCity("San Francisco");
        address2.setStreet("Lakeside");
        address2.setNumber("110");

        addressRepository.save(address);
        addressRepository.save(address2);
        createAndInsertPerson("John",address, LocalDate.of(1989,12,10),"Married");
        createAndInsertPerson("Laura",address, LocalDate.of(1988,07,15),"Married");
        createAndInsertPerson("Michael",address2, LocalDate.of(1999,10,23),"Not Married");

    }

    public void createAndInsertPerson(String name, Address address, LocalDate birthDate, String civilStatus){
        Person person = new Person();
        person.setName(name);
        person.setAddress(address);
        person.setBirthDate(birthDate);
        person.setCivilStatus(civilStatus);
        personRepository.save(person);
    }

}
