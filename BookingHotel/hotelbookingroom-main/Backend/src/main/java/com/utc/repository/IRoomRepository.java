package com.utc.repository;

import com.utc.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IRoomRepository extends JpaRepository<Room,Integer>, JpaSpecificationExecutor<Room> {

    @Query(nativeQuery = true)
    public Page<Room> getRoomByStatusRoom(String string, Pageable pageable);

    @Query(nativeQuery = true)
    public Page<Room> getRoomByHotelName(String hotelName,Pageable pageable);

    @Query(nativeQuery = true)
    public Page<Room> getRoomByStatusRoomAndHotelName(String status,String hotelName,Pageable pageable);

    @Query(nativeQuery = true)
    public Page<Room> getRoomByRoomTypeName(String roomType,Pageable pageable);

    @Query(nativeQuery = true)
    public Room getRoomByNumberAndHotelName(String number,String hotelName);

    @Query(nativeQuery = true)
    public Room getRoomByNumber(String number);

}
