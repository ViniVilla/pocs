package com.example.planner.mapper;

import com.example.planner.domain.Plan;
import com.example.planner.domain.request.PlanRequest;

public interface PlanMapper {

    static Plan requestToEntity(final PlanRequest request){
        return Plan.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .dateTime(request.getDateTime())
                    .build();
    }

}
