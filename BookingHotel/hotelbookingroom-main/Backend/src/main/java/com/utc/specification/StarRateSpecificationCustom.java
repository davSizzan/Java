package com.utc.specification;

import com.utc.entity.StarRate;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
public class StarRateSpecificationCustom implements Specification<StarRate> {

    @NonNull
    private String filter;

    @NonNull
    private Object value;


    @Override
    public Predicate toPredicate(Root<StarRate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (filter.equalsIgnoreCase("hotelName")){
            return criteriaBuilder.like(root.get("hotel").get("name"),"%" + value.toString() + "%");
        }
        return null;
    }
}
