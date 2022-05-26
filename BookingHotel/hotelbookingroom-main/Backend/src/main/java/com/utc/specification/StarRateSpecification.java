package com.utc.specification;

import com.utc.entity.StarRate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class StarRateSpecification {

    public static Specification<StarRate> buildWhere(String search){

        Specification<StarRate> where = null;

        if (!StringUtils.isEmpty(search)){
            search = search.trim();
            StarRateSpecificationCustom nameHotel = new StarRateSpecificationCustom("hotelName",search);

            where = Specification.where(nameHotel);
        }
        return where;
    }
}
