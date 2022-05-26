package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`RoomBook`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class RoomBook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rb_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "rb_rId",referencedColumnName = "r_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "rb_bId",referencedColumnName = "b_id")
    private Booking booking;
}
