package com.utc.specification;

import com.utc.entity.RoomRateDiscount;
import com.utc.form.filter.RoomRateDiscountFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class RoomRateDiscountSpecification {

    public static Specification<RoomRateDiscount> buildWhere(String search, RoomRateDiscountFilter filter){
        Specification<RoomRateDiscount> where = null;

        if (!StringUtils.isEmpty(search)){
            search = search.trim();
            RoomRateDiscountSpecificationCustom rate = new RoomRateDiscountSpecificationCustom("rate",search);

            where = Specification.where(rate);
        }

        if (filter!= null && filter.getStartMonth() != null){
            RoomRateDiscountSpecificationCustom startDate = new RoomRateDiscountSpecificationCustom("startMonth",filter.getStartMonth());
            if (where == null){
                where = startDate;
            }else {
                where = where.and(startDate);
            }
        }

        return where;
    }
}
