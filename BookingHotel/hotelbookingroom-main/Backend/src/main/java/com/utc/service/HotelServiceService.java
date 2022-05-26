package com.utc.service;

import com.utc.entity.Hotel;
import com.utc.entity.HotelServices;
import com.utc.form.create.HotelServiceCreateForm;
import com.utc.form.filter.HotelServiceFilter;
import com.utc.form.update.HotelServiceUpdateForm;
import com.utc.repository.IHotelRepository;
import com.utc.repository.IHotelServicesRepository;
import com.utc.specification.HotelServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HotelServiceService implements IHotelServiceService{

    @Autowired
    private IHotelServicesRepository hotelServicesRepository;

    @Autowired
    private IHotelRepository hotelRepository;

    @Override
    public Page<HotelServices> getListHotelService(String search, HotelServiceFilter filter, Pageable pageable) {
        Specification<HotelServices> where = HotelServiceSpecification.buildWhere(search, filter);
        return hotelServicesRepository.findAll(where,pageable);
    }

    @Override
    public List<HotelServices> getListHotelServicesByHotelName(String hotelName) {
        return hotelServicesRepository.getHotelServicesByHotelName(hotelName);
    }

    @Override
    public void createHotelService(HotelServiceCreateForm form) {
        Hotel hotel = hotelRepository.findByName(form.getHotelName());
        HotelServices hotelServices = new HotelServices();
        hotelServices.setName(form.getName());
        hotelServices.setCost(form.getCost());
        hotelServices.setHotel(hotel);

        hotelServicesRepository.save(hotelServices);
    }

    @Override
    public void updateHotelService(String name,String hotelName,HotelServiceUpdateForm form) {
        HotelServices hotelServices = hotelServicesRepository.getHotelServicesByNameAndHotelName(name,hotelName);
        hotelServices.setCost(form.getCost());

        hotelServicesRepository.save(hotelServices);
    }

    @Override
    public void deleteHotelService(String hotelName) {

    }


}
