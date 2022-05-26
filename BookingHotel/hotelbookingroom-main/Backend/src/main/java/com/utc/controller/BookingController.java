package com.utc.controller;

import com.utc.dto.BookingDTO;
import com.utc.entity.Booking;
import com.utc.form.create.BookingCreateForm;
import com.utc.form.update.BookingUpdateForm;
import com.utc.service.IBookingService;
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
@RequestMapping("/UTCDemo/booking")
@Validated
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/byGuestsIdCard")
    @PreAuthorize("hasAnyAuthority('ADMIN','GUESTS')")
    public ResponseEntity<?> getBookingByGuestsIdCard(@RequestParam(name = "idCard") String idCard, Pageable pageable){
        Page<Booking> pageBooking = bookingService.getListBookingByIdCard(idCard, pageable);
        List<BookingDTO> bookingDTOList = modelMapper.map(pageBooking.getContent(), new TypeToken<List<BookingDTO>>(){}.getType());
        Page<BookingDTO> dtoPage = new PageImpl<>(bookingDTOList,pageable,pageBooking.getTotalElements());

        return new ResponseEntity<>(dtoPage,HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('GUESTS')")
    public ResponseEntity<?> createBooking(@RequestParam(name = "idCard") String idCard, @RequestBody BookingCreateForm form){
        bookingService.createBooking(idCard, form);
        return new ResponseEntity<>("Create Success!!!",HttpStatus.OK);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('GUESTS')")
    public ResponseEntity<?> updateBooking(@RequestParam(name = "idCard") String idCard, @RequestBody @Valid BookingUpdateForm form){
        bookingService.updateBooking(idCard, form);
        return new ResponseEntity<>("Update Success!!!",HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('GUESTS')")
    public ResponseEntity<?> deleteBookingId(int id){
        bookingService.deleteBookingById(id);
        return new  ResponseEntity<>("Delete Booking Success",HttpStatus.OK);
    }

    @DeleteMapping("/deleteByStatus")
    public ResponseEntity<?> deleteBookingByStatus(@RequestParam(name = "status") String status){
        bookingService.deleteAllByStatus(status);
        return new  ResponseEntity<>("Delete Booking By Status",HttpStatus.OK);
    }
}
