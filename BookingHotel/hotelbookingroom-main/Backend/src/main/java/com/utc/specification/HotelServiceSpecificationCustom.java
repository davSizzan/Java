package com.utc.specification;

import com.utc.entity.HotelServices;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
public class HotelServiceSpecificationCustom implements Specification<HotelServices> {

    @NonNull
    private String filter;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<HotelServices> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (filter.equalsIgnoreCase("name")){
            return criteriaBuilder.like(root.get("name"),"%" + value.toString() + "%");
        }

        if (filter.equalsIgnoreCase("cost")){
            return criteriaBuilder.equal(root.get("cost"),value);
        }

        if(filter.equalsIgnoreCase("hotelName")){
            return criteriaBuilder.like(root.get("hotel").get("name"),"%" + value.toString() + "%");
        }
        return null;
    }
}
