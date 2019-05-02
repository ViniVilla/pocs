package com.poc.specification.repository;

import com.poc.specification.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepository extends JpaRepository<Person,Long>, JpaSpecificationExecutor<Person> {
}
