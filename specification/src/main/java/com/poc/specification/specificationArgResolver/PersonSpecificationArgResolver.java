package com.poc.specification.specificationArgResolver;

import com.poc.specification.entity.Person;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Disjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Disjunction({
        @And({@Spec(path="name", params="name", spec= Equal.class),
             @Spec(path="birthDate", params={"firstDate","lastDate"}, spec= Between.class),
             @Spec(path="civilStatus",spec= In.class),
             @Spec(path="address.street",params="streetName",spec=Equal.class)})
})
public interface PersonSpecificationArgResolver extends Specification<Person> {
}
