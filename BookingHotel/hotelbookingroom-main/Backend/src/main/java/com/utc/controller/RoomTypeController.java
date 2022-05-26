package com.utc.controller;

import com.utc.dto.RoomTypeDTO;
import com.utc.entity.RoomType;
import com.utc.form.create.RoomTypeCreateForm;
import com.utc.form.filter.RoomTypeFilter;
import com.utc.form.update.RoomTypeUpdateForm;
import com.utc.service.IRoomTypeService;
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
@RequestMapping("/UTCDemo/roomType")
@Validated
@CrossOrigin("*")
public class RoomTypeController {

    @Autowired
    private IRoomTypeService roomTypeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','GUESTS')")
    public ResponseEntity<?> getListRoomType(){
        List<RoomType> list = roomTypeService.getListRoomType();
        List<RoomTypeDTO> dtoList = modelMapper.map(list,new TypeToken<List<RoomTypeDTO>>(){}.getType());

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/page")
    @PreAuthorize("hasAnyAuthority('ADMIN','GUESTS')")
    public ResponseEntity<?> getListRoomTypeByPage(String search, RoomTypeFilter filter, Pageable pageable){
        Page<RoomType> page = roomTypeService.getAllByPage(search,filter,pageable);
        List<RoomTypeDTO> roomTypeDTOS = modelMapper.map(page.getContent(),new TypeToken<List<RoomTypeDTO>>(){}.getType());
        Page<RoomTypeDTO> typeDTOS = new PageImpl<>(roomTypeDTOS,pageable,page.getTotalElements());

        return new ResponseEntity<>(typeDTOS,HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createRoomType(@RequestBody @Valid RoomTypeCreateForm form){
        roomTypeService.createRoomType(form);
        return new ResponseEntity<>("Create Success!!",HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateRoomType(@RequestParam(name = "id") int id,@RequestBody @Valid RoomTypeUpdateForm form){
        roomTypeService.updateRoomType(id,form);
        return new ResponseEntity<>("Update Success!!",HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteRoomType(@RequestParam(value = "id") int id){
        roomTypeService.deleteRoomType(id);
        return new ResponseEntity<>("Delete Success !!",HttpStatus.OK);
    }

}
