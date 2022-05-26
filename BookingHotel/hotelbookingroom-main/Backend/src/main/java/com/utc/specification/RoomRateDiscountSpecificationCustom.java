package com.utc.specification;

import com.utc.entity.RoomRateDiscount;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

@Data
public class RoomRateDiscountSpecificationCustom implements Specification<RoomRateDiscount> {

    @NonNull
    private String filter;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<RoomRateDiscount> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (filter.equalsIgnoreCase("startMonth")){
            return criteriaBuilder.greaterThanOrEqualTo(root.get("startMonth").as(java.util.Date.class),(Date) value);
        }

        if(filter.equalsIgnoreCase("rate")){
            return criteriaBuilder.equal(root.get("rate"),value);
        }

        return null;
    }
}
