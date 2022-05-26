package com.utc.service;

import com.utc.entity.RoomType;
import com.utc.form.create.RoomTypeCreateForm;
import com.utc.form.filter.RoomTypeFilter;
import com.utc.form.update.RoomTypeUpdateForm;
import com.utc.repository.IRoomTypeRepository;
import com.utc.specification.RoomTypeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomTypeService implements IRoomTypeService{

    @Autowired
    private IRoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomType> getListRoomType() {
        return roomTypeRepository.findAll();
    }

    @Override
    public Page<RoomType> getAllByPage(String search, RoomTypeFilter filter, Pageable pageable) {
        Specification<RoomType> where = RoomTypeSpecification.buildWhere(search, filter);
        return roomTypeRepository.findAll(where,pageable);
    }

    @Override
    public void createRoomType(RoomTypeCreateForm form) {
        RoomType roomType = new RoomType();
        roomType.setName(form.getName());
        roomType.setCost(form.getCost());
        roomType.setTypeRoom(RoomType.TypeRoom.toEnum(form.getTypeRoom()));

        roomTypeRepository.save(roomType);
    }

    @Override
    public void updateRoomType(int id,RoomTypeUpdateForm form) {
        RoomType roomType = roomTypeRepository.findById(id).get();
        roomType.setCost(form.getCost());

        roomTypeRepository.save(roomType);
    }

    @Override
    public void deleteRoomType(int id) {
        RoomType roomType = roomTypeRepository.findById(id).get();
        roomTypeRepository.delete(roomType);
    }
}
