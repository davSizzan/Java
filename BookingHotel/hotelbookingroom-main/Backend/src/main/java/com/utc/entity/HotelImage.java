package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`HotelImage`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class HotelImage implements Serializable {

    @Id
    @Column(name = "hi_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hi_image_name",nullable = false,unique = true)
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "hi_hId",referencedColumnName = "h_id")
    private Hotel hotel;
}
