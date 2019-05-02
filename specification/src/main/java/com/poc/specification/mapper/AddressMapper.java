package com.poc.specification.mapper;

import com.poc.specification.dto.AddressDto;
import com.poc.specification.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto entityToDto(AddressDto addressDto);
    @Mapping(target = "residents", ignore = true)
    Address dtoToEntity(AddressDto addressDto);

    List<AddressDto> entitiesToDtos(List<Address> addresses);
    List<Address> dtosToEntities(List<AddressDto> addressDtos);

}
