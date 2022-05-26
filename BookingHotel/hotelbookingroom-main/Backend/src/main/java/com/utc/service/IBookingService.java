package com.utc.service;

import com.utc.entity.Booking;
import com.utc.form.create.BookingCreateForm;
import com.utc.form.filter.BookingFilter;
import com.utc.form.update.BookingUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookingService {

    public Page<Booking> getListBookingByPage(String search, BookingFilter filter, Pageable pageable);

    public  Page<Booking> getListBookingByIdCard(String idCard,Pageable pageable);

    public void updateBooking(String idCard, BookingUpdateForm form);

    public void createBooking(String idCard,BookingCreateForm form);

    public void deleteBookingById(int id);

    public void deleteAllByStatus(String status);
}
