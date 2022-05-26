package com.utc.service;

import com.utc.entity.StarRate;
import com.utc.form.create.StarRateCreateForm;
import com.utc.form.update.StarRateUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStarRateService {

    public List<StarRate> getAllListStarRate();

    public List<StarRate> getListStarRateByHotelName(String hotelName);

    public Page<StarRate> getPageStarRateByHotelName(String hotelName, Pageable pageable);

    public void createStarRate(StarRateCreateForm form);

    public void updateStarRate(int id, StarRateUpdateForm form);

    public void deleteStarRate(int id);

    public void deleteMultipleImage(List<Integer> ids);
}
