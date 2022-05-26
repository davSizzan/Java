package com.utc.service;

import com.utc.entity.Hotel;
import com.utc.entity.HotelServices;
import com.utc.form.create.HotelServiceCreateForm;
import com.utc.form.filter.HotelServiceFilter;
import com.utc.form.update.HotelServiceUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface IHotelServiceService {

    public Page<HotelServices> getListHotelService(String search, HotelServiceFilter filter, Pageable pageable);

    public List<HotelServices> getListHotelServicesByHotelName(String hotelName);

    public void createHotelService(HotelServiceCreateForm form);

    public void updateHotelService(String name,String hotelName,HotelServiceUpdateForm form);

    public void deleteHotelService(String hotelName);

}
