package com.utc.service;

import com.utc.entity.RoomRateDiscount;
import com.utc.form.create.RoomRateDiscountCreateForm;
import com.utc.form.filter.RoomRateDiscountFilter;
import com.utc.form.update.RoomRateDiscountUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoomRateDiscountService {

    public List<RoomRateDiscount> getListRoomRateDiscount();

    public Page<RoomRateDiscount> getListRoomRateDiscountByPage(String search, RoomRateDiscountFilter filter, Pageable pageable);

    public void createRoomRateDiscount(RoomRateDiscountCreateForm form);

    public void updateRoomRateDiscount(RoomRateDiscountUpdateForm form);

    public void deleteRoomRateDiscount(int id);
}
