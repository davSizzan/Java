package com.utc.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "`HotelServices`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class HotelServices implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`hs_id`")
    private int id;

    @Column(name = "`hs_name`",length = 100,nullable = false)
    private String name;

    @Column(name = "`hs_cost`",nullable = false)
    private double cost;

    @ManyToOne
    @JoinColumn(name = "`hs_hId`",referencedColumnName = "`h_id`")
    private Hotel hotel;

    @OneToMany(mappedBy = "hotelServices")
    private List<UserServices> userServices;
}
