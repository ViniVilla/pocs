package com.example.planner.service;

import com.example.planner.domain.Plan;
import com.example.planner.domain.request.PlanRequest;

import java.util.List;

public interface PlanService {

    Long create(Long personId, PlanRequest request);

    void delete(Long personId, Long id);

    List<Plan> findAllByOwner(Long personId);

}
