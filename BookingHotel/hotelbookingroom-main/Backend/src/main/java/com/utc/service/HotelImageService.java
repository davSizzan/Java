package com.utc.service;

import com.utc.entity.Hotel;
import com.utc.entity.HotelImage;
import com.utc.form.create.HotelImageCreateForm;
import com.utc.form.update.HotelImageUpdateForm;
import com.utc.repository.IHotelImageRepository;
import com.utc.repository.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HotelImageService implements IHotelImageService{

    @Autowired
    private IHotelImageRepository hotelImageRepository;

    private IHotelRepository hotelRepository;

    @Override
    public List<HotelImage> getAllListImageByName(String nameHotel) {
        return hotelImageRepository.findByHotel_Name(nameHotel);
    }

    @Override
    public void createHotelImage(HotelImageCreateForm form) {
        Hotel hotel = hotelRepository.findByName(form.getHotelName());
        HotelImage hotelImage = new HotelImage();
        hotelImage.setImageName(form.getImageName());
        hotelImage.setHotel(hotel);

        hotelImageRepository.save(hotelImage);
    }

    @Override
    public void updateHotelImage(int idHotel,HotelImageUpdateForm form) {
//        Hotel hotel = hotelRepository.findById(idHotel).get();
        HotelImage hotelImage = hotelImageRepository.findById(idHotel).get();
        hotelImage.setImageName(form.getImageName());

        hotelImageRepository.save(hotelImage);
    }

    @Override
    public void deleteHotelImageByHotelId(int id) {
        hotelImageRepository.deleteHotelImageByHotel_Id(id);
    }
}
