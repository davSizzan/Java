package com.utc.controller;

import com.utc.dto.UserServicesDTO;
import com.utc.entity.UserServices;
import com.utc.form.create.UserServiceCreateForm;
import com.utc.service.IUserServicesService;
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
@RequestMapping("/UTCDemo/userServices")
@Validated
@CrossOrigin("*")
public class UseServicesController {

    @Autowired
    private IUserServicesService userServicesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','GUESTS')")
    public ResponseEntity<?> getListUserServices(){
        List<UserServices> list = userServicesService.getListUserServices();
        List<UserServicesDTO> userServicesDTOList = modelMapper.map(list,new TypeToken<List<UserServicesDTO>>(){}.getType());
        return new ResponseEntity<>(userServicesDTOList, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('GUESTS')")
    public ResponseEntity<?> createUserServices(@RequestBody @Valid UserServiceCreateForm form){
        userServicesService.createUserServices(form);
        return new ResponseEntity<>("Create User Services Success!!!", HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('GUESTS')")
    public ResponseEntity<?> deleteUserServices(@RequestParam(name = "hotelServicesName") String hotelServicesName,@RequestParam(name = "bookingId") int bookingId){
        userServicesService.deleteUserServices(hotelServicesName, bookingId);
        return new ResponseEntity<>("Delete User Services Success!!!", HttpStatus.OK);
    }
}
