package com.utc.service;

import com.utc.entity.RoomBook;
import com.utc.form.create.RoomBookCreateForm;
import com.utc.form.update.RoomBookUpdateForm;

import java.util.List;

public interface IRoomBookService {

    public List<RoomBook> getListRoomBook();

    public List<RoomBook> getRoomBookByBookingGuestsId(int guestsId);

    public void createRoomBook(RoomBookCreateForm form);

    public void updateRoomBook(int id,RoomBookUpdateForm form);

    public void deleteRoomBook(int id);

    public RoomBook getRoomBookById(int id);

    public List<RoomBook> getRoomBookByGuestsIdCard(String idCard);

}
