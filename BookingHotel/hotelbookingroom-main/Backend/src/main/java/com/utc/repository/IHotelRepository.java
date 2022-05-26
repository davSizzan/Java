package com.utc.repository;

import com.utc.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface IHotelRepository extends JpaRepository<Hotel,Integer>, JpaSpecificationExecutor<Hotel> {

    public Page<Hotel> getHotelByAddress_City (String city, Pageable pageable);

    @Query(nativeQuery = true)
    public boolean existsHotelById(int id);

    @Query(nativeQuery = true)
    public Hotel findByName(String name);
}
