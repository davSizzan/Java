package com.utc.repository;

import com.utc.entity.RoomType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRoomTypeRepository extends JpaRepository<RoomType,Integer>, JpaSpecificationExecutor<RoomType> {
}
