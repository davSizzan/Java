package com.utc.service;

import com.utc.entity.Booking;
import com.utc.entity.HotelServices;
import com.utc.entity.UserServices;
import com.utc.form.create.UserServiceCreateForm;
import com.utc.repository.IBookingRepository;
import com.utc.repository.IHotelServicesRepository;
import com.utc.repository.IUserServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServicesService implements IUserServicesService{

    @Autowired
    private IUserServicesRepository userServicesRepository;

    @Autowired
    private IHotelServicesRepository hotelServicesRepository;

    @Autowired
    private IBookingRepository bookingRepository;

    @Override
    public List<UserServices> getListUserServices() {
        return userServicesRepository.findAll();
    }

    @Override
    public void createUserServices(UserServiceCreateForm form) {
        HotelServices hotelServices = hotelServicesRepository.getHotelServicesByName(form.getHotelServicesName());
        Booking booking = bookingRepository.findById(form.getBookingId()).get();
        UserServices userServices = new UserServices();
        userServices.setHotelServices(hotelServices);
        userServices.setBooking(booking);

        userServicesRepository.save(userServices);
    }

    @Override
    public void deleteUserServices(String hotelServicesName, int bookingId) {
        userServicesRepository.deleteByHotelServicesNameAndBookingId(hotelServicesName, bookingId);
    }
}
