package com.utc.repository;

import com.utc.entity.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHotelImageRepository extends JpaRepository<HotelImage,Integer> , JpaSpecificationExecutor<HotelImage> {

    @Modifying
    public void deleteHotelImageByHotel_Id(int id);


    public List<HotelImage> findByHotel_Name(String name);
}
