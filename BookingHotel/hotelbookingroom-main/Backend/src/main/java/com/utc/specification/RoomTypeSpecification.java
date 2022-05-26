package com.utc.specification;

import com.utc.entity.RoomType;
import com.utc.form.filter.RoomTypeFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class RoomTypeSpecification {

    public static Specification<RoomType> buildWhere(String search, RoomTypeFilter filter){
        Specification<RoomType> where = null;

        if (!StringUtils.isEmpty(search)){
            search = search.trim();
            RoomTypeSpecificationCustom name = new RoomTypeSpecificationCustom("name",search);
            where = Specification.where(name);
        }

        if (filter != null && filter.getRoomType() != null){
            RoomTypeSpecificationCustom type = new RoomTypeSpecificationCustom("roomType",filter.getRoomType());

            if (where == null){
                where = type;
            }
            else {
                where = where.and(type);
            }
        }

        if (filter != null && filter.getRoomCost() != null){
            RoomTypeSpecificationCustom cost = new RoomTypeSpecificationCustom("roomCost",filter.getRoomCost());
            if (where == null){
                where = cost;
            }else {
                where = where.and(cost);
            }
        }

        if (filter != null && filter.getMinCost() != null){
            RoomTypeSpecificationCustom minCost = new RoomTypeSpecificationCustom("minCost",filter.getMinCost());
            if (where == null){
                where = minCost;
            }else {
                where = where.and(minCost);
            }
        }

        if(filter != null && filter.getMaxCost() != null){
            RoomTypeSpecificationCustom maxCost = new RoomTypeSpecificationCustom("maxCost",filter.getMaxCost());
            if (where == null){
                where = maxCost;
            }else {
                where = where.and(maxCost);
            }
        }
        return where;
    }
}
