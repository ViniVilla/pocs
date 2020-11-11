package com.example.planner.domain.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class PlanRequest {

    private String name;

    private String description;

    private LocalDateTime dateTime;

}
