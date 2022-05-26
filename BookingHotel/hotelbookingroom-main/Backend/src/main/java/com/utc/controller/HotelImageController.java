package com.utc.controller;

import com.utc.dto.HotelImageDTO;
import com.utc.entity.HotelImage;
import com.utc.form.create.HotelImageCreateForm;
import com.utc.form.update.HotelImageUpdateForm;
import com.utc.service.IHotelImageService;
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
@RequestMapping("/UTCDemo/image")
@Validated
@CrossOrigin("*")
public class HotelImageController {

    @Autowired
    private IHotelImageService hotelImageService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getListImageByHotelName(@RequestParam(name = "hotelName") String hotelName){
        List<HotelImage> list = hotelImageService.getAllListImageByName(hotelName);
        List<HotelImageDTO> dtoList = modelMapper.map(list,new TypeToken<List<HotelImageDTO>>(){}.getType());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createHotelImage(@RequestBody @Valid HotelImageCreateForm form){
        hotelImageService.createHotelImage(form);
        return new ResponseEntity<>("Create Image Hotel Success!!",HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateHotelImage(@RequestParam(name = "id") int id,@RequestBody @Valid HotelImageUpdateForm form){
        hotelImageService.updateHotelImage(id, form);
        return new ResponseEntity<>("Update Image Hotel Success!!",HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteHotelImageByHotelId(@RequestParam(name = "id") int id){
        hotelImageService.deleteHotelImageByHotelId(id);
        return new ResponseEntity<>("Delete Image Hotel Success!!",HttpStatus.OK);
    }
}
