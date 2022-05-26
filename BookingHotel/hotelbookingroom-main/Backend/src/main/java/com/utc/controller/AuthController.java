package com.utc.controller;

import com.utc.dto.responseJWT.ResponseJWTDTO;
import com.utc.dto.signin_up.SignInDTO;
import com.utc.dto.signin_up.SignUpDTO;
import com.utc.entity.Address;
import com.utc.entity.Guests;
import com.utc.form.create.AddressInsertForm;
import com.utc.repository.IGuestsRepository;
import com.utc.service.IAddressService;
import com.utc.service.IGuestsService;
import com.utc.utils.JwtUtils;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/UTCAuth")
@Validated
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private IGuestsService iGuestsService;

    @Autowired
    private IGuestsRepository guestsRepository;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInDTO signInDTO){
        System.out.println(signInDTO.toString());
        Guests guests = guestsRepository.getGuestsByUserName(signInDTO.getUserName());
        if (guests == null){
            throw new UsernameNotFoundException(signInDTO.getUserName());
        }

        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInDTO.getUserName(),
                        signInDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new  ResponseEntity<>(
                new ResponseJWTDTO(
                        token,
                        userDetails.getUsername(),
                        userDetails.getAuthorities().toString()),HttpStatus.OK);
//        return new ResponseEntity<>("SignIn Success!!",HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDTO signUpDTO){
        System.out.println(signUpDTO.toString());
        Address address = addressService.getAddressByCityAndAndCountry(signUpDTO.getAddressCity(),signUpDTO.getAddressCountry());
        Guests guests = modelMapper.map(signUpDTO,Guests.class);
        System.out.println(guests.toString());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passEncoder = bCryptPasswordEncoder.encode(signUpDTO.getPassword());
        if (address == null){
            AddressInsertForm insertForm = new AddressInsertForm();
            insertForm.setCountry(signUpDTO.getAddressCountry());
            insertForm.setCity(signUpDTO.getAddressCity());
            addressService.createAddress(insertForm);
            Address addressNew = addressService.getAddressByCityAndAndCountry(signUpDTO.getAddressCity(),signUpDTO.getAddressCountry());
            guests.setAddress(addressNew);
            guests.setPassword(passEncoder);
            iGuestsService.createGuestsByGuests(guests);

        }else {
            guests.setAddress(address);
            guests.setPassword(passEncoder);
            iGuestsService.createGuestsByGuests(guests);
        }
        JSONObject message = new JSONObject();
        message.put("status", 200);
        message.put("resultText", "Register account successfully!");
        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }
}
