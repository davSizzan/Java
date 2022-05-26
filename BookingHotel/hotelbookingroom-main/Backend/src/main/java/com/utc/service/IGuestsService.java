package com.utc.service;

import com.utc.entity.Guests;
import com.utc.form.create.GuestsCreateForm;
import com.utc.form.filter.GuestsFilter;
import com.utc.form.update.GuestsUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;
import java.util.List;

public interface IGuestsService extends UserDetailsService {

    public List<Guests> findAllGuests();

    public Page<Guests> findAllByPage(String search, Pageable pageable, GuestsFilter filter);

    public Guests getGuestsByUserName(String userName);

    public void createGuests(GuestsCreateForm form);

    public void createGuestsByGuests(Guests guests);

    public void updateGuests(int id,GuestsUpdateForm form);

    public void deleteGuests(int id);

    public Guests getGuestsByIdCard(String idCard);

}
