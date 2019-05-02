package com.poc.specification.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PersonDto {

    private Long id;
    private String name;
    private Date birthDate;
    private String civilStatus;
    private String address;

}
