package com.utc.service;

import com.utc.entity.RoomRateDiscount;
import com.utc.entity.RoomType;
import com.utc.form.create.RoomRateDiscountCreateForm;
import com.utc.form.filter.RoomRateDiscountFilter;
import com.utc.form.update.RoomRateDiscountUpdateForm;
import com.utc.repository.IRoomRateDiscountRepository;
import com.utc.repository.IRoomTypeRepository;
import com.utc.specification.RoomRateDiscountSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomRateDiscountService implements IRoomRateDiscountService{

    @Autowired
    private IRoomRateDiscountRepository rateDiscountRepository;

    @Autowired
    private IRoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomRateDiscount> getListRoomRateDiscount() {
        return rateDiscountRepository.findAll();
    }

    @Override
    public Page<RoomRateDiscount> getListRoomRateDiscountByPage(String search, RoomRateDiscountFilter filter, Pageable pageable) {
        Specification<RoomRateDiscount> where = RoomRateDiscountSpecification.buildWhere(search, filter);
        return rateDiscountRepository.findAll(where,pageable);
    }

    @Override
    public void createRoomRateDiscount(RoomRateDiscountCreateForm form) {
        RoomRateDiscount roomRateDiscount = new RoomRateDiscount();
        RoomType roomType = roomTypeRepository.findById(form.getRoomTypeId()).get();

        roomRateDiscount.setRate(form.getRate());
        roomRateDiscount.setStartMonth(form.getStartMonth());
        roomRateDiscount.setEndMonth(form.getEndMonth());
        roomRateDiscount.setRoomType(roomType);

        rateDiscountRepository.save(roomRateDiscount);
    }

    @Override
    public void updateRoomRateDiscount(RoomRateDiscountUpdateForm form) {
        RoomRateDiscount roomRateDiscount = new RoomRateDiscount();
        roomRateDiscount.setRate(form.getRate());
        roomRateDiscount.setStartMonth(form.getStartMonth());
        roomRateDiscount.setEndMonth(form.getEndMonth());

        rateDiscountRepository.save(roomRateDiscount);
    }

    @Override
    public void deleteRoomRateDiscount(int id) {
        RoomRateDiscount roomRateDiscount = rateDiscountRepository.findById(id).get();
        rateDiscountRepository.delete(roomRateDiscount);
    }
}
