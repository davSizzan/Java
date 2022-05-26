package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`StarRate`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class StarRate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sr_id")
    private int id;

    @Column(name = "sr_image",length = 100)
    private int image;

    @ManyToOne
    @JoinColumn(name = "sr_hId",referencedColumnName = "h_id")
    private Hotel hotel;
}
