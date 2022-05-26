package com.utc.controller;

import com.utc.dto.AddressDTO;
import com.utc.entity.Address;
import com.utc.form.create.AddressInsertForm;
import com.utc.form.filter.AddressFilter;
import com.utc.form.update.AddressUpdateForm;
import com.utc.service.IAddressService;
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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/UTCDemo/address")
@Validated
@CrossOrigin("*")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> listAddress(){
        List<Address> addressList = addressService.findAllAddress();
        List<AddressDTO> addressDTOS = modelMapper.map(addressList,new TypeToken<List<AddressDTO>>(){}.getType());
        return new ResponseEntity<List<AddressDTO>>(addressDTOS, HttpStatus.OK);
    }

    @GetMapping("/page")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> listAddressByPaging(@RequestParam(required = false,name = "search") Integer search, Pageable pageable, AddressFilter filter){
        Page<Address> page = addressService.findAllAddressByPage(search,pageable,filter);
        List<AddressDTO> dtos = modelMapper.map(page.getContent(),new TypeToken<List<AddressDTO>>(){}.getType());
        Page<AddressDTO> addressDTOPage = new PageImpl<>(dtos,pageable,page.getTotalElements());

        return new ResponseEntity<Page<AddressDTO>>(addressDTOPage,HttpStatus.OK);
    }

    @PostMapping("/createAddress")
    @PreAuthorize("hasAnyAuthority('GUESTS','ADMIN')")
    public ResponseEntity<?> createAddress(@RequestBody @Valid AddressInsertForm form){
        addressService.createAddress(form);

        return new ResponseEntity<>("Create success!!",HttpStatus.OK);
    }

    @PutMapping("/updateAddress")
    @PreAuthorize("hasAnyAuthority('GUESTS','ADMIN')")
    public ResponseEntity<?> updateAddress(@RequestParam(name = "id") int id,@RequestBody @Valid AddressUpdateForm form){
        addressService.updateAddress(id, form);

        return new ResponseEntity<>("Update Success!!!",HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('GUESTS','ADMIN')")
    public ResponseEntity<?> deleteAddress(@RequestParam(name = "id") int id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Delete Success!!!",HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllAddress")
    @PreAuthorize("hasAnyAuthority('GUESTS','ADMIN')")
    public ResponseEntity<?> deleteAllAddress(@RequestParam List<Integer> ids){
        addressService.deleteAllAddress(ids);
        return new ResponseEntity<>("Delete All Address Success!!!",HttpStatus.OK);
    }
}
