package com.utc.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "`Guests`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Guests implements Serializable {

    @Id
    @Column(name = "g_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "g_first_name",nullable = false,length = 100)
    private String firstName;

    @Column(name = "g_last_name",nullable = false,length = 100)
    private String lastName;

    @Column(name = "g_user_name",nullable = false,length = 100)
    private String userName;

    @Column(name = "g_idCard",nullable = false,length = 100,unique = true)
    private String idCard;

    @Column(name = "g_credit_card",length = 100,unique = true)
    private String creditCard;

    @Column(name = "g_email",nullable = false,length = 100,unique = true)
    private String email;

    @Column(name = "g_password",length = 200,nullable = false)
    private String password;

    @Column(name = "g_type",nullable = false)
    @ColumnDefault("ENUM('GUESTS','ADMIN') DEFAULT 'GUESTS'")
    @Enumerated(EnumType.STRING)
    private GuestsType guestsType;

    @ManyToOne
    @JoinColumn(name = "g_add_id",referencedColumnName = "add_id")
    private Address address;

    @Formula("concat(g_first_name,' ',g_last_name)")
    private String fullName;

    @OneToMany(mappedBy = "guests")
    private List<Booking> bookings;

    public enum GuestsType{
        GUESTS,ADMIN;
        public static GuestsType toEnum(String strClient){
            for (GuestsType item : GuestsType.values()){
                if (item.toString().equalsIgnoreCase(strClient)){
                    return item;
                }
            }
            return null;
        }
    }
}
