package com.utc.specification;

import com.utc.entity.HotelServices;
import com.utc.form.filter.HotelServiceFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class HotelServiceSpecification {

    public static Specification<HotelServices> buildWhere(String search, HotelServiceFilter filter){
        Specification<HotelServices> where = null;

        if (!StringUtils.isEmpty(search)){
            search = search.trim();
            HotelServiceSpecificationCustom name = new HotelServiceSpecificationCustom("name",search);

            where = Specification.where(name);
        }

        if (filter != null && filter.getCost() != null){
            HotelServiceSpecificationCustom cost = new HotelServiceSpecificationCustom("cost","%" + filter.getCost() + "%");

            if (where == null){
                where = cost;
            }else{
                where = where.and(cost);
            }
        }

        if(filter != null && filter.getHotelName() != null){
            HotelServiceSpecificationCustom hotelName = new HotelServiceSpecificationCustom("hotelName","%" + filter.getHotelName() + "%");
            if (where == null){
                where = hotelName;
            }else {
                where = where.and(hotelName);
            }
        }

        return where;
    }
}
