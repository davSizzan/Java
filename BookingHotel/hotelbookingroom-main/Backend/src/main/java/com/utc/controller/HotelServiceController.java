package com.utc.controller;

import com.utc.dto.HotelServicesDTO;
import com.utc.entity.HotelServices;
import com.utc.form.create.HotelServiceCreateForm;
import com.utc.form.filter.HotelServiceFilter;
import com.utc.form.update.HotelServiceUpdateForm;
import com.utc.service.HotelService;
import com.utc.service.IHotelServiceService;
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
@RequestMapping("/UTCDemo/hotelServices")
@Validated
@CrossOrigin("*")
public class HotelServiceController {

    @Autowired
    private IHotelServiceService hotelServiceService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("permitAll()")
    private ResponseEntity<?> getListHotelService(@RequestParam(name = "search",required = false) String search, HotelServiceFilter filter, Pageable pageable){
        Page<HotelServices> hotelServicesPage = hotelServiceService.getListHotelService(search, filter, pageable);
        List<HotelServicesDTO> hotelServicesDTOS = modelMapper.map(hotelServicesPage.getContent(),new TypeToken<List<HotelServicesDTO>>(){}.getType());
        Page<HotelServicesDTO> hotelServicesDTOPage = new PageImpl<>(hotelServicesDTOS,pageable,hotelServicesPage.getTotalElements());

        return new ResponseEntity<>(hotelServicesDTOPage, HttpStatus.OK);
    }

    @GetMapping("/byHotelName")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getListHotelServicesByHotelName(@RequestParam(name = "hotelName") String hotelName){
        List<HotelServices> hotelServicesList = hotelServiceService.getListHotelServicesByHotelName(hotelName);
        List<HotelServicesDTO> hotelServicesDTOS = modelMapper.map(hotelServicesList,new TypeToken<List<HotelServicesDTO>>(){}.getType());
        return new ResponseEntity<>(hotelServicesDTOS,HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createHotelService(@RequestBody @Valid HotelServiceCreateForm form){
        hotelServiceService.createHotelService(form);
        return new ResponseEntity<>("Create Hotel Service Success!!!",HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateHotelService(@RequestParam(name = "name") String name,@RequestParam(name = "hotelName") String hotelName,@RequestBody @Valid HotelServiceUpdateForm form){
        hotelServiceService.updateHotelService(name, hotelName, form);
        return new ResponseEntity<>("Update Hotel Service Success!!!",HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteHotelService(@RequestParam(name = "hotelName") String hotelName){
        hotelServiceService.deleteHotelService(hotelName);
        return new ResponseEntity<>("Delete Hotel Service Success By Name!!!",HttpStatus.OK);
    }
}
