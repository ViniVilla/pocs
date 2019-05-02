package com.poc.specification.specificationManual;

import com.poc.specification.entity.Person;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonSpecificationManualBuilder {

    private final List<SearchCriteria> params;

    public PersonSpecificationManualBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public PersonSpecificationManualBuilder with(String key, String operation, Object value, Boolean or) {
        params.add(new SearchCriteria(key, operation, value, or));
        return this;
    }

    public Specification<Person> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(PersonSpecificationManual::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);
        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).getOr()
                    ? Specification.where(result).and(specs.get(i))
                    : Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}


