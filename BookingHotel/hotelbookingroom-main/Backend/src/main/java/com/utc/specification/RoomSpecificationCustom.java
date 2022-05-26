package com.utc.specification;

import com.utc.entity.Room;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
public class RoomSpecificationCustom implements Specification<Room> {

    @NonNull
    private String filter;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (filter.equalsIgnoreCase("number")){
            return  criteriaBuilder.like(root.get("number"),"%" + value.toString() + "%");
        }

        if (filter.equalsIgnoreCase("roomType")){
            return criteriaBuilder.like(root.get("roomType").get("name"),"%" + value.toString() + "%");
        }

        if (filter.equalsIgnoreCase("statusRoom")){
            return criteriaBuilder.equal(root.get("statusRoom"),Room.StatusRoom.toEnum(value.toString()));
        }

        if (filter.equalsIgnoreCase("hotelName")){
            return criteriaBuilder.like(root.get("hotel").get("name"),"%" + value.toString() + "%");
        }
        return null;
    }
}
