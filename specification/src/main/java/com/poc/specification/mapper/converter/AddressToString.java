package com.poc.specification.mapper.converter;

import com.poc.specification.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressToString {

    public String addressToString(Address address){
        return address.getStreet();
    }

}
