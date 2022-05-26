package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "`Hotel`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "h_id")
    private int id;

    @Column(name = "h_name",length = 100,nullable = false,unique = true)
    private String name;

    @Column(name = "h_email_address",length = 100,nullable = false,unique = true)
    private String email;

    @Column(name = "h_website",length = 100, nullable = false,unique = true)
    private String website;

    @Column(name = "h_description",length = 100,nullable = false)
    private String description;

    @Column(name = "h_roomCount",nullable = false)
    private int roomCount;

    @ManyToOne
    @JoinColumn(name = "`h_addId`",referencedColumnName = "`add_id`")
    private Address address;

}
