package com.example.planner.service.impl;

import com.example.planner.domain.Plan;
import com.example.planner.domain.request.PlanRequest;
import com.example.planner.mapper.PlanMapper;
import com.example.planner.repository.PlanRepository;
import com.example.planner.service.PersonService;
import com.example.planner.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    private final PersonService personService;

    public PlanServiceImpl(final PlanRepository planRepository,
                           final PersonService personService) {
        this.planRepository = planRepository;
        this.personService = personService;
    }

    @Override
    public Long create(final Long personId, final PlanRequest request) {
        log.info("M=create, personId={}, request={}", personId, request);
        final var person = personService.find(personId);

        Plan entity = PlanMapper.requestToEntity(request);
        entity.setOwner(person);

        planRepository.save(entity);
        return entity.getId();
    }

    @Override
    public void delete(final Long personId, final Long id) {
        log.info("M=delete, personId={}, id={}", personId, id);
        final var owner = personService.find(personId);

        planRepository.deleteByIdAndOwner(id, owner);
    }

    @Override
    public List<Plan> findAllByOwner(final Long personId) {
        log.info("M=findAllByOwner, personId={}", personId);
        final var owner = personService.find(personId);

        return planRepository.findAllByOwner(owner);
    }
}
