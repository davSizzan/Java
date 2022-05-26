package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "`Booking`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`b_id`")
    private int id;

    @Column(name = "`b_date`",nullable = false,columnDefinition = "DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateBooking;

    @Column(name = "`b_durationOfStay`")
    private int timeLive;

    @Column(name = "`b_checkInDate`",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkIn;

    @Column(name = "`b_checkOutDate`",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOut;

    @Column(name = "`b_typePayment`",nullable = false,columnDefinition = "DEFAULT 'DIRECT'")
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @Column(name = "`b_totalRoom`",nullable = false,columnDefinition = "DEFAULT 1")
    private int totalRoom;

    @ManyToOne
    @JoinColumn(name = "b_hId",referencedColumnName = "h_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "b_gId",referencedColumnName = "g_id")
    private Guests guests;

    @Column(name = "b_totalAmount")
    private double amount;

    @Column(name = "b_status",nullable = false,columnDefinition = "DEFAULT 'UNPAID'")
    @Enumerated(EnumType.STRING)
    private bStatus status;

    @OneToMany(mappedBy = "booking")
    private List<RoomBook> roomBooks;

    @OneToMany(mappedBy = "booking")
    private List<UserServices> userServices;
    public enum PaymentType{
        BAKING,DIRECT;
        public static PaymentType toEnum(String strClient){
            for (PaymentType item : PaymentType.values()){
                if (item.toString().equalsIgnoreCase(strClient)){
                    return item;
                }
            }
            return null;
        }
    }

    public enum bStatus{
        UNPAID,PAID;

        public static bStatus toEnum(String strClient){
            for (bStatus item : bStatus.values()){
                if (item.toString().equalsIgnoreCase(strClient)){
                    return item;
                }
            }
            return null;
        }
    }
}
