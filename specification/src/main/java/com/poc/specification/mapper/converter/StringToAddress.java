package com.poc.specification.mapper.converter;

import com.poc.specification.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class StringToAddress {

    public Address stringToAddress(String string){
        final Address address = new Address();
        address.setStreet(string);
        return address;
    }

}
