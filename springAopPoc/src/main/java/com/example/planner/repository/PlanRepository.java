package com.example.planner.repository;

import com.example.planner.domain.Person;
import com.example.planner.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findAllByOwner(Person owner);

    void deleteByIdAndOwner(Long id, Person owner);

}
