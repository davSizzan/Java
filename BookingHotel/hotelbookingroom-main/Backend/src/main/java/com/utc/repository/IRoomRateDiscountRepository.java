package com.utc.repository;

import com.utc.entity.RoomRateDiscount;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRoomRateDiscountRepository extends JpaRepository<RoomRateDiscount,Integer>, JpaSpecificationExecutor<RoomRateDiscount> {
}
