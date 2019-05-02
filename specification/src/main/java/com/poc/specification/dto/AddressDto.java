package com.poc.specification.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Long id;
    private String city;
    private String street;
    private String number;

}
