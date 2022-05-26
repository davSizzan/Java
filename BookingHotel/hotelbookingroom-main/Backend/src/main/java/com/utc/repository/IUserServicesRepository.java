package com.utc.repository;

import com.utc.entity.UserServices;
import com.utc.entity.doublepk.UserServiceKey;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IUserServicesRepository extends JpaRepository<UserServices, UserServiceKey>{

        @Transactional
        @Modifying
        @Query("delete from UserServices u where u.hotelServices.name = ?1 and u.booking.id = ?2")
        public void deleteByHotelServicesNameAndBookingId(String hotelServicesName, int bookingId);
}
