package com.utc.specification;

import com.utc.entity.Booking;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

@Data
public class BookingSpecificationCustom implements Specification<Booking> {

    @NonNull
    private String filter;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<Booking> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (filter.equalsIgnoreCase("guestsIdCard")){
            return criteriaBuilder.like(root.get("guests").get("idCard"),"%" + value.toString() + "%");
        }

        if(filter.equalsIgnoreCase("checkInDate")){
            return criteriaBuilder.equal(root.get("checkIn").as(java.util.Date.class),(Date) value);
        }

        if(filter.equalsIgnoreCase("checkOutDate")){
            return criteriaBuilder.equal(root.get("checkOut").as(java.util.Date.class),(Date) value);
        }

        return null;
    }
}
