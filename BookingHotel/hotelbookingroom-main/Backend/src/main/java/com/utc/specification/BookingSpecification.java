package com.utc.specification;

import com.utc.entity.Booking;
import com.utc.form.filter.BookingFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BookingSpecification {

    public static Specification<Booking> buildWhere(String search, BookingFilter filter){
        Specification<Booking> where = null;

        if (!StringUtils.isEmpty(search)){
            search = search.trim();
            BookingSpecificationCustom idCard = new BookingSpecificationCustom("guestsIdCard",search);
            where = Specification.where(idCard);
        }

        if (filter != null && filter.getCheckInDate() != null){
            BookingSpecificationCustom checkInDate = new BookingSpecificationCustom("checkInDate",filter.getCheckInDate());
            if (where == null){
                where = checkInDate;
            }
            else {
                where = where.and(checkInDate);
            }
        }

        if (filter != null && filter.getCheckOutDate() != null){
            BookingSpecificationCustom checkOutDate = new BookingSpecificationCustom("checkInDate",filter.getCheckOutDate());
            if (where == null){
                where = checkOutDate;
            }
            else {
                where = where.and(checkOutDate);
            }
        }

        return where;
    }
}
