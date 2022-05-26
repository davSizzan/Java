package com.utc.service;

import com.utc.entity.Hotel;
import com.utc.entity.StarRate;
import com.utc.form.create.StarRateCreateForm;
import com.utc.form.update.StarRateUpdateForm;
import com.utc.repository.IHotelRepository;
import com.utc.repository.IStarRateRepository;
import com.utc.specification.StarRateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StarRateService implements IStarRateService{

    @Autowired
    private IStarRateRepository starRateRepository;

    @Autowired
    private IHotelRepository hotelRepository;

    @Override
    public List<StarRate> getAllListStarRate() {
        return starRateRepository.findAll();
    }

    @Override
    public List<StarRate> getListStarRateByHotelName(String hotelName) {
        return starRateRepository.findByHotel_Name(hotelName);
    }

    @Override
    public Page<StarRate> getPageStarRateByHotelName(String search, Pageable pageable) {
        Specification<StarRate> where = StarRateSpecification.buildWhere(search);
        return starRateRepository.findAll(where,pageable);
    }

    @Override
    public void createStarRate(StarRateCreateForm form) {
        StarRate starRate = new StarRate();
        Hotel hotel = hotelRepository.findById(form.getHotelId()).get();
        starRate.setImage(form.nameImage);
        starRate.setHotel(hotel);

        starRateRepository.save(starRate);
    }

    @Override
    public void updateStarRate(int id, StarRateUpdateForm form) {
        StarRate starRate = starRateRepository.findById(id).get();
        starRate.setImage(form.getImageName());

        starRateRepository.save(starRate);
    }

    @Override
    public void deleteStarRate(int id) {
        StarRate starRate = starRateRepository.findById(id).get();
        starRateRepository.delete(starRate);
    }

    @Override
    public void deleteMultipleImage(List<Integer> ids) {
        starRateRepository.deleteMultipleImage(ids);
    }
}
