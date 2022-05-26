package com.utc.repository;

import com.utc.entity.Address;
import com.utc.form.filter.AddressFilter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAddressRepository extends JpaRepository<Address,Integer> , JpaSpecificationExecutor<Address> {

    @Query(value = "DELETE FROM Address add WHERE add.id IN :ids")
    @Modifying
    public void deleteAllAddress(List<Integer> ids);

    public Address findAddressByCountry(String country);

    public Address findAddressByCity(String city);

    public boolean existsAddressByCity(String city);

    public boolean existsAddressByCountry(String country);

    @Query("select a from Address a where a.city = ?1 and a.country = ?2")
    public Address getAddressByCityAndAndCountry(String city, String country);

    public Address findAddressByCityAndCountry(String city,String country);

}
