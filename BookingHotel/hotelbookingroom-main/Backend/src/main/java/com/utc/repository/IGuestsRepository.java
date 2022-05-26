package com.utc.repository;

import com.utc.entity.Guests;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IGuestsRepository extends JpaRepository<Guests,Integer>, JpaSpecificationExecutor<Guests> {

    @Query(value = "SELECT guests FROM Guests guests")
    public List<Guests> getAll();

    public Guests findGuestsByUserName(String userName);

    public Guests getGuestsByUserName(String userName);

    public Guests getGuestsByIdCard(String idCard);
}
