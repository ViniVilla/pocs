package com.example.planner.resource;

import com.example.planner.aop.LogEndpoint;
import com.example.planner.domain.Plan;
import com.example.planner.domain.request.PlanRequest;
import com.example.planner.service.PlanService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons/{personId}/plans")
public class PlanResource {

    private final PlanService planService;

    public PlanResource(final PlanService planService) {
        this.planService = planService;
    }

    @LogEndpoint
    @PostMapping
    public void create(@PathVariable("personId") final Long personId,
                       @RequestBody final PlanRequest request){
        planService.create(personId, request);
    }

    @LogEndpoint
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("personId") final Long personId,
                       @PathVariable("id") final Long id){
        planService.delete(personId, id);
    }

    @LogEndpoint
    @GetMapping
    public List<Plan> findAllByOwner(@PathVariable("personId") final Long personId){
        return planService.findAllByOwner(personId);
    }

}
