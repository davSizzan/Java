package com.utc.controller;

import com.utc.dto.RoomBookClientDTO;
import com.utc.dto.RoomBookDTO;
import com.utc.entity.Room;
import com.utc.entity.RoomBook;
import com.utc.form.create.RoomBookCreateForm;
import com.utc.form.create.RoomCreateForm;
import com.utc.form.update.RoomBookUpdateForm;
import com.utc.form.update.RoomUpdateFrom;
import com.utc.service.IRoomBookService;
import com.utc.service.IRoomService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/UTCDemo/roomBook")
@Validated
@CrossOrigin("*")
public class RoomBookController {

    @Autowired
    private IRoomBookService roomBookService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','GUESTS')")
    public ResponseEntity<?> getListRoomBook(){
        List<RoomBook> roomBookList = roomBookService.getListRoomBook();
        List<RoomBookDTO> roomBookDTOList = modelMapper.map(roomBookList,new TypeToken<List<RoomBookDTO>>(){}.getType());

        return new ResponseEntity<>(roomBookDTOList, HttpStatus.OK);
    }

    @GetMapping("/guestsId")
    @PreAuthorize("hasAnyAuthority('ADMIN','GUESTS')")
    public ResponseEntity<?> getRoomBookByBookingGuestsId(@RequestParam(name = "guestsId") int guestsId){
        List<RoomBook> roomBookList = roomBookService.getRoomBookByBookingGuestsId(guestsId);
        List<RoomBookDTO> roomBookDTOList = modelMapper.map(roomBookList,new TypeToken<List<RoomBookDTO>>(){}.getType());

        return new ResponseEntity<>(roomBookDTOList, HttpStatus.OK);
    }

    @GetMapping("/byIdCard")
    @PreAuthorize("hasAuthority('GUESTS')")
    public ResponseEntity<?> getRoomBookByGuestsIdCard(@RequestParam(name = "idCard") String idCard){
        List<RoomBook> roomBookList = roomBookService.getRoomBookByGuestsIdCard(idCard);
        List<RoomBookClientDTO> roomBookClientDTOS = modelMapper.map(roomBookList,new TypeToken<List<RoomBookClientDTO>>(){}.getType());

        return new ResponseEntity<>(roomBookClientDTOS,HttpStatus.OK);
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','GUESTS')")
    public ResponseEntity<?> createRoomBooking(@RequestBody @Valid RoomBookCreateForm form){
        roomBookService.createRoomBook(form);
        return new ResponseEntity<>("Create Success!!!", HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','GUESTS')")
    public ResponseEntity<?> updateRoomBooking(@RequestParam(name = "id")int id,@RequestBody @Valid RoomBookUpdateForm form){
        roomBookService.updateRoomBook(id,form);
        return new ResponseEntity<>("Update Success!!!", HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','GUESTS')")
    public ResponseEntity<?> deleteRoomBooking(@RequestParam(name = "id") int id){
        RoomBook roomBook = roomBookService.getRoomBookById(id);
        roomBookService.deleteRoomBook(id);
        return new ResponseEntity<>("Delete Success!!!", HttpStatus.OK);
    }
}
