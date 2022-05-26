package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "`Room`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private int id;

    @Column(name = "r_number",nullable = false)
    private String number;

    @Column(name = "r_status",nullable = false,columnDefinition = "DEFAULT 'DRUM'")
    @Enumerated(EnumType.STRING)
    private StatusRoom statusRoom;

    @ManyToOne
    @JoinColumn(name = "r_rtId",referencedColumnName = "rt_id")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "r_hId",referencedColumnName = "h_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private List<RoomBook> roomBooks;

    public enum StatusRoom{
        DRUM,BOOKING;
        public static StatusRoom toEnum(String strClient){
            for (StatusRoom item : StatusRoom.values()){
                if (item.toString().equals(strClient)){
                    return item;
                }
            }
            return null;
        }
    }
}
