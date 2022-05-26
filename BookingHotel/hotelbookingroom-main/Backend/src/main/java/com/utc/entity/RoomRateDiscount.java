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

@Table(name = "`RoomRateDiscount`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class RoomRateDiscount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`rrd_id`")
    private int id;

    @Column(name = "`rrd_rate`",nullable = false)
    private double rate;

    @Column(name = "`rrd_startMonth`",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startMonth;

    @Column(name = "`rrd_endMonth`",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endMonth;

    @ManyToOne
    @JoinColumn(name = "`rrd_rtId`",referencedColumnName = "`rt_id`")
    private RoomType roomType;
}
