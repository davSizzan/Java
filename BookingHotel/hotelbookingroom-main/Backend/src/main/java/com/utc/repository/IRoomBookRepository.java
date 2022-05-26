package com.utc.repository;

import com.utc.entity.RoomBook;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRoomBookRepository extends JpaRepository<RoomBook,Integer>, JpaSpecificationExecutor<Integer> {

    public List<RoomBook> getRoomBookByBookingGuestsId(int guestsId);

    public List<RoomBook> getRoomBookByBookingGuestsIdCard(String idCard);
}
