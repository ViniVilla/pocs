package com.poc.specification.mapper;

import com.poc.specification.dto.PersonDto;
import com.poc.specification.entity.Person;
import com.poc.specification.mapper.converter.AddressToString;
import com.poc.specification.mapper.converter.StringToAddress;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressToString.class, StringToAddress.class})
public interface PersonMapper {

    PersonDto entityToDto(Person invoice);
    Person dtoToEntity(PersonDto invoiceDto);
    List<PersonDto> entitiesToDtos(List<Person> invoices);
    List<Person> dtosToEntities(List<PersonDto> invoiceDtos);

}
