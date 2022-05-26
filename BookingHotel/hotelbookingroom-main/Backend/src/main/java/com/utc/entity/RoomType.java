package com.utc.entity;

import com.utc.entity.enumconvert.RoomTypeStatusConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "`RoomType`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class RoomType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`rt_id`")
    private int id;

    @Column(name = "`rt_name`",length = 100,nullable = false)
    private String name;

    @Column(name = "`rt_cost`",nullable = false)
    private double cost;

    @Column(name = "`rt_description`",nullable = false,columnDefinition = "DEFAULT 'TRAVEL'")
    @Convert(converter = RoomTypeStatusConverter.class)
    private TypeRoom typeRoom;

    @OneToMany(mappedBy = "roomType")
    private List<RoomRateDiscount> roomRateDiscount;

    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;
    public enum TypeRoom{
        T("TRAVEL"),GOB("GO ON BUSSINESS"),R("RESORT");

        private String status;

        private TypeRoom(String status){
            this.status = status;
        }
        public String getStatus(){
            return status;
        }

        public static TypeRoom toEnum(String strClient){
            for (TypeRoom item : TypeRoom.values()){
                if (item.getStatus().equalsIgnoreCase(strClient)){
                    return item;
                }
            }
            return null;
        }

    }

}
