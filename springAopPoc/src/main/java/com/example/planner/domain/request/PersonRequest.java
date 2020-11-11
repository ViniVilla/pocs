package com.example.planner.domain.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class PersonRequest {

    private String name;

    private LocalDate birthDate;

}
