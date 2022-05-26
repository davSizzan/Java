package com.utc.service;

import com.utc.entity.Room;
import com.utc.form.create.RoomCreateForm;
import com.utc.form.filter.RoomFilter;
import com.utc.form.update.RoomUpdateFrom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoomService {

    public List<Room> getListRoom();

    public Page<Room> getListRoomByPage(String search, RoomFilter filter, Pageable pageable);

    public Page<Room> getListRoomByStatus(String status,Pageable pageable);

    public Page<Room> getListRoomByHotelName(String hotelName,Pageable pageable);

    public Page<Room> getListRoomByType(String roomType,Pageable pageable);

    public Page<Room> getListRoomByStatusRoomAndHotelName(String status,String hotelName,Pageable pageable);

    public void createRoom(RoomCreateForm form);

    public void updateRoom(int id, RoomUpdateFrom from);

    public void deleteRoom(int id);

    public Room getRoomByNumberAndHotelName(String number,String hotelName);

    public Room getRoomByNumber(String number);

}
