package com.utc.repository;

import com.utc.entity.StarRate;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStarRateRepository extends JpaRepository<StarRate,Integer>, JpaSpecificationExecutor<StarRate> {

    @Query(nativeQuery = true)
    public List<StarRate> findByHotel_Name(String hotelName);

    @Query(value = "DELETE FROM StarRate WHERE id IN (:ids)")
    @Modifying
    public void deleteMultipleImage(List<Integer> ids);

}
