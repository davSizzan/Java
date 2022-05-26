package com.utc.specification;

import com.utc.entity.RoomType;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
public class RoomTypeSpecificationCustom implements Specification<RoomType> {

    @NonNull
    private String filter;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<RoomType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (filter.equalsIgnoreCase("roomName")){
            return criteriaBuilder.like(root.get("name"),"%" + value.toString() + "%");
        }

        if (filter.equalsIgnoreCase("roomType")){
            return criteriaBuilder.like(root.get("typeRoom"),"%" + RoomType.TypeRoom.toEnum(value.toString()) + "%");
        }

        if (filter.equalsIgnoreCase("roomCost")){
            return criteriaBuilder.equal(root.get("cost"),value);
        }

        if (filter.equalsIgnoreCase("minCost")){
            return criteriaBuilder.greaterThanOrEqualTo(root.get("cost"),(Double) value);
        }

        if (filter.equalsIgnoreCase("maxCost")){
            return criteriaBuilder.lessThanOrEqualTo(root.get("cost"),(Double) value);
        }
        return null;
    }
}
