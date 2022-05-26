package com.utc.controller;

import com.utc.dto.RoomDTO;
import com.utc.entity.Room;
import com.utc.form.create.RoomCreateForm;
import com.utc.form.filter.RoomFilter;
import com.utc.form.update.RoomUpdateFrom;
import com.utc.service.IRoomService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/UTCDemo/room")
@Validated
@CrossOrigin("*")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getListRoom(){
        List<Room> list = roomService.getListRoom();
        List<RoomDTO> roomDTOS = modelMapper.map(list,new TypeToken<List<RoomDTO>>(){}.getType());

        return new ResponseEntity<>(roomDTOS, HttpStatus.OK);
    }

    @GetMapping("/page")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getListRoomByPage(@RequestParam(name = "search",required = false) String search, RoomFilter filter, Pageable pageable){
        Page<Room> list = roomService.getListRoomByPage(search, filter, pageable);
        List<RoomDTO> roomDTOS = modelMapper.map(list.getContent(),new TypeToken<List<RoomDTO>>(){}.getType());
        Page<RoomDTO> roomDTOPage = new PageImpl<>(roomDTOS,pageable,list.getTotalElements());
        return new ResponseEntity<>(roomDTOPage, HttpStatus.OK);
    }

    @GetMapping("/pageStatus")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getListRoomByStatus(@RequestParam(name = "status") String status,Pageable pageable){
        Page<Room> list = roomService.getListRoomByStatus(status, pageable);
        List<RoomDTO> roomDTOS = modelMapper.map(list.getContent(),new TypeToken<List<RoomDTO>>(){}.getType());
        Page<RoomDTO> roomDTOPage = new PageImpl<>(roomDTOS,pageable,list.getTotalElements());
        return new ResponseEntity<>(roomDTOPage, HttpStatus.OK);
    }

    @GetMapping("/pageHotelName")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getListRoomByHotelName(@RequestParam(name = "hotelName") String hotelName,Pageable pageable){
        Page<Room> list = roomService.getListRoomByHotelName(hotelName, pageable);
        List<RoomDTO> roomDTOS = modelMapper.map(list.getContent(),new TypeToken<List<RoomDTO>>(){}.getType());
        Page<RoomDTO> roomDTOPage = new PageImpl<>(roomDTOS,pageable,list.getTotalElements());
        return new ResponseEntity<>(roomDTOPage, HttpStatus.OK);
    }

    @GetMapping("/pageRoomType")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getListRoomByType(@RequestParam(name = "roomType") String roomType,Pageable pageable){
        Page<Room> list = roomService.getListRoomByType(roomType, pageable);
        List<RoomDTO> roomDTOS = modelMapper.map(list.getContent(),new TypeToken<List<RoomDTO>>(){}.getType());
        Page<RoomDTO> roomDTOPage = new PageImpl<>(roomDTOS,pageable,list.getTotalElements());
        return new ResponseEntity<>(roomDTOPage, HttpStatus.OK);
    }

    @GetMapping("/pageRoomTypeAndHotelName")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getListRoomByStatusRoomAndHotelName(@RequestParam(name = "status") String status,@RequestParam(name = "hotelName") String hotelName,Pageable pageable){
        Page<Room> list = roomService.getListRoomByStatusRoomAndHotelName(status, hotelName, pageable);
        List<RoomDTO> roomDTOS = modelMapper.map(list.getContent(),new TypeToken<List<RoomDTO>>(){}.getType());
        Page<RoomDTO> roomDTOPage = new PageImpl<>(roomDTOS,pageable,list.getTotalElements());
        return new ResponseEntity<>(roomDTOPage, HttpStatus.OK);
    }

    @GetMapping("/getRoomByNumberAndHotelName")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getRoomByNumberAndHotelName(@RequestParam(name = "number") String number,@RequestParam(name = "hotelName") String hotelName){
        Room room = roomService.getRoomByNumberAndHotelName(number, hotelName);
        RoomDTO roomDTO = modelMapper.map(room,RoomDTO.class);
        return new ResponseEntity<>(roomDTO,HttpStatus.OK);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createRoom(@RequestBody @Valid RoomCreateForm form){
        roomService.createRoom(form);
        return new ResponseEntity<>("Create Success!!",HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateRoom(@RequestParam(name = "id") int id,@RequestBody @Valid RoomUpdateFrom form){
        roomService.updateRoom(id,form);
        return new ResponseEntity<>("Create Success!!",HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteRoom(@RequestParam(name = "id") int id){
        roomService.deleteRoom(id);
        return new ResponseEntity<>("Delete Success!!",HttpStatus.OK);
    }
}
