package com.utc.entity;

import com.utc.entity.doublepk.UserServiceKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`UserServices`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class UserServices implements Serializable {

    @EmbeddedId
    private UserServiceKey key;

    @ManyToOne
    @JoinColumn(name = "us_bId")
    @MapsId("us_bId")
    private Booking booking;


    @ManyToOne
    @JoinColumn(name = "us_hsId")
    @MapsId("us_hsId")
    private HotelServices hotelServices;
}
