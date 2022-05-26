package com.utc.service;

import com.utc.entity.Address;
import com.utc.entity.Guests;
import com.utc.form.create.GuestsCreateForm;
import com.utc.form.filter.GuestsFilter;
import com.utc.form.update.GuestsUpdateForm;
import com.utc.repository.IAddressRepository;
import com.utc.repository.IGuestsRepository;
import com.utc.specification.GuestsSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GuestsService implements IGuestsService{

    @Autowired
    private IGuestsRepository guestsRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Override
    public List<Guests> findAllGuests() {
        return guestsRepository.getAll();
    }

    @Override
    public Page<Guests> findAllByPage(String search, Pageable pageable, GuestsFilter filter) {
        Specification<Guests> where = GuestsSpecification.buildWhere(search, filter);
        return guestsRepository.findAll(where,pageable);
    }

    @Override
    public Guests getGuestsByUserName(String userName) {
        return guestsRepository.getGuestsByUserName(userName);
    }


    @Override
    public void createGuests(GuestsCreateForm form) {
        Guests guests = new Guests();

        guests.setFirstName(form.getFirstName());
        guests.setLastName(form.getLastName());
        guests.setIdCard(form.getIdCard());
        guests.setEmail(form.getEmail());

        Address address = addressRepository.findAddressByCityAndCountry(form.getCity(), form.getCountry());
        if (address == null){

            Address addressCreate = new Address();
            addressCreate.setCity(form.getCity());
            addressCreate.setCountry(form.getCountry());
            addressRepository.save(addressCreate);

            guests.setAddress(addressCreate);
            guestsRepository.save(guests);
        }else {
            guests.setAddress(address);
            guestsRepository.save(guests);
        }

    }

    @Override
    public void createGuestsByGuests(Guests guests) {
        guestsRepository.save(guests);
    }

    @Override
    public void updateGuests(int id, GuestsUpdateForm form) {
        Guests guests = guestsRepository.findById(id).get();
        guests.setEmail(form.getEmail());
        guests.setCreditCard(form.getCreditCard());
        guestsRepository.save(guests);
    }

    @Override
    public void deleteGuests(int id) {
        Guests guests = guestsRepository.findById(id).get();
        guestsRepository.delete(guests);
    }

    @Override
    public Guests getGuestsByIdCard(String idCard) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Guests guests = guestsRepository.getGuestsByUserName(userName);
        if (guests == null){
            throw new UsernameNotFoundException(userName);
        }
        if (guests.getGuestsType() != null){
            return new User(guests.getUserName()
                    ,guests.getPassword()
                    , AuthorityUtils.createAuthorityList(guests.getGuestsType().toString()));
        }else {
            return new User(guests.getUserName()
                    ,guests.getPassword()
                    , AuthorityUtils.createAuthorityList("GUESTS"));
        }
    }
}
