package com.utc.service;

import com.utc.entity.Hotel;
import com.utc.entity.Room;
import com.utc.entity.RoomType;
import com.utc.form.create.RoomCreateForm;
import com.utc.form.filter.RoomFilter;
import com.utc.form.update.RoomUpdateFrom;
import com.utc.repository.IHotelRepository;
import com.utc.repository.IRoomRepository;
import com.utc.repository.IRoomTypeRepository;
import com.utc.specification.RoomSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomService implements IRoomService{

    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IRoomTypeRepository roomTypeRepository;

    @Autowired
    private IHotelRepository hotelRepository;

    @Override
    public List<Room> getListRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Page<Room> getListRoomByPage(String search, RoomFilter filter, Pageable pageable) {
        Specification<Room> where = RoomSpecification.buildWhere(search, filter);
        return roomRepository.findAll(where,pageable);
    }

    @Override
    public Page<Room> getListRoomByStatus(String status, Pageable pageable) {

        return roomRepository.getRoomByStatusRoom(status,pageable);
    }

    @Override
    public Page<Room> getListRoomByHotelName(String hotelName, Pageable pageable) {
        return roomRepository.getRoomByHotelName(hotelName, pageable);
    }

    @Override
    public Page<Room> getListRoomByType(String roomType, Pageable pageable) {

        return roomRepository.getRoomByRoomTypeName(roomType,pageable);
    }

    @Override
    public Page<Room> getListRoomByStatusRoomAndHotelName(String status, String hotelName, Pageable pageable) {
        return roomRepository.getRoomByStatusRoomAndHotelName(status, hotelName, pageable);
    }

    @Override
    public void createRoom(RoomCreateForm form) {

        Room room = new Room();
        RoomType roomType = roomTypeRepository.findById(form.getRoomType()).get();
        Hotel hotel = hotelRepository.findByName(form.getHotelName());

        room.setNumber(form.getNumber());
        room.setRoomType(roomType);
        room.setHotel(hotel);

        roomRepository.save(room);

    }

    @Override
    public void updateRoom(int id, RoomUpdateFrom from) {
        Room rom = roomRepository.findById(id).get();
        rom.setStatusRoom(Room.StatusRoom.toEnum(from.getStatus()));
        roomRepository.save(rom);
    }

    @Override
    public void deleteRoom(int id) {
        Room room = roomRepository.findById(id).get();
        roomRepository.delete(room);
    }

    @Override
    public Room getRoomByNumberAndHotelName(String number, String hotelName) {
        return roomRepository.getRoomByNumberAndHotelName(number, hotelName);
    }

    @Override
    public Room getRoomByNumber(String number) {
        return roomRepository.getRoomByNumber(number);
    }
}
