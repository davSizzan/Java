package com.utc.service;

import com.utc.entity.HotelImage;
import com.utc.form.create.HotelImageCreateForm;
import com.utc.form.update.HotelImageUpdateForm;

import java.util.List;

public interface IHotelImageService {

    public List<HotelImage> getAllListImageByName(String nameHotel);

    public void createHotelImage(HotelImageCreateForm form);

    public void updateHotelImage(int idHotel,HotelImageUpdateForm form);

    public void deleteHotelImageByHotelId(int id);
}
