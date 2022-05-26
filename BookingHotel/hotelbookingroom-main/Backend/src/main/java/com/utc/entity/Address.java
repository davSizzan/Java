package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "`Address`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`add_id`")
    private int id;

    @Column(name = "`add_country`",length = 100,nullable = false)
    private String country;

    @Column(name = "`add_city`",length = 100,nullable = false)
    private String city;

//    @OneToMany(mappedBy = "g_addId",fetch = FetchType.EAGER)
//    private List<Guests> guests;

    public Address(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }
}
