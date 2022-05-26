package com.utc.specification;

import com.utc.entity.Room;
import com.utc.form.filter.RoomFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class RoomSpecification {

    public static Specification<Room> buildWhere(String search, RoomFilter filter){

        Specification<Room> where = null;
        if (!StringUtils.isEmpty(search)){
            search = search.trim();
            RoomSpecificationCustom number = new RoomSpecificationCustom("number",search);
            where = Specification.where(number);
        }

        if (filter != null && filter.getStatusRoom() != null){
            RoomSpecificationCustom roomStatus = new RoomSpecificationCustom("statusRoom",filter.getStatusRoom());
            if (where == null){
                where = roomStatus;
            }
            else {
                where = where.and(roomStatus);
            }
        }

        if (filter != null && filter.getRoomType() != null){
            RoomSpecificationCustom roomType = new RoomSpecificationCustom("roomType",filter.getRoomType());
            if (where == null){
                where = roomType;
            }
            else {
                where = where.and(roomType);
            }
        }

        if (filter != null && filter.getHotelName() != null){
            RoomSpecificationCustom hotelName = new RoomSpecificationCustom("hotelName",filter.getHotelName());
            if (where == null){
                where = hotelName;
            }
            else {
                where = where.and(hotelName);
            }
        }

        return where;
    }
}
