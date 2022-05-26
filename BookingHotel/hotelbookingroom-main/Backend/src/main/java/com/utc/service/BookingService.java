package com.utc.service;

import com.utc.entity.Booking;
import com.utc.entity.Guests;
import com.utc.entity.Hotel;
import com.utc.form.create.BookingCreateForm;
import com.utc.form.filter.BookingFilter;
import com.utc.form.update.BookingUpdateForm;
import com.utc.repository.*;
import com.utc.specification.BookingSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookingService implements IBookingService{

    @Autowired
    private IBookingRepository bookingRepository;

    @Autowired
    private IGuestsRepository guestsRepository;

    @Autowired
    private IHotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<Booking> getListBookingByPage(String search, BookingFilter filter, Pageable pageable) {
        Specification<Booking> where = BookingSpecification.buildWhere(search, filter);
        return bookingRepository.findAll(where,pageable);
    }

    @Override
    public Page<Booking> getListBookingByIdCard(String idCard, Pageable pageable) {
        return bookingRepository.getBookingByGuestsIdCard(idCard,pageable);
    }

    @Override
    public void updateBooking(String idCard, BookingUpdateForm form) {
        List<Booking> booking = bookingRepository.getBookingByGuestsIdCard(idCard);
        for (Booking item : booking){
            item.setType(Booking.PaymentType.toEnum(form.getPaymentType()));
            item.setStatus(Booking.bStatus.toEnum(form.getStatus()));
            bookingRepository.save(item);
        }

    }

    @Override
    public void createBooking(String idCard,BookingCreateForm form) {
        Booking booking = new Booking();
        Guests guests = guestsRepository.getGuestsByIdCard(idCard);
        Hotel hotel = hotelRepository.findByName(form.getHotelName());
        booking.setCheckIn(form.getCheckIn());
        booking.setCheckOut(form.getCheckOut());
        booking.setGuests(guests);
        booking.setHotel(hotel);
        bookingRepository.save(booking);
    }

    @Override
    public void deleteBookingById(int id) {
        Booking booking = bookingRepository.findById(id).get();
        bookingRepository.delete(booking);
    }

    @Override
    public void deleteAllByStatus(String status) {
        bookingRepository.deleteAllByStatus(status);
    }
}
