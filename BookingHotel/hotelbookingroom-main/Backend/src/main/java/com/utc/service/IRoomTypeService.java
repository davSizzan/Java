package com.utc.service;

import com.utc.entity.RoomType;
import com.utc.form.create.RoomTypeCreateForm;
import com.utc.form.filter.RoomTypeFilter;
import com.utc.form.update.RoomTypeUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoomTypeService {

    public List<RoomType> getListRoomType();

    public Page<RoomType> getAllByPage(String search, RoomTypeFilter filter, Pageable pageable);

    public void createRoomType(RoomTypeCreateForm form);

    public void updateRoomType(int id,RoomTypeUpdateForm form);

    public void  deleteRoomType(int id);
}
