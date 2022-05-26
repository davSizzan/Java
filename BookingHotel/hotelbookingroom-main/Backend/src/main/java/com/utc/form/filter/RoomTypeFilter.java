package com.utc.form.filter;

import lombok.Data;

@Data
public class RoomTypeFilter {

    private String roomName;

    private String roomType;

    private Double roomCost;

    private Double minCost;

    private Double maxCost;
}
