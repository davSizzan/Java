package com.utc.service;

import com.utc.entity.UserServices;
import com.utc.form.create.UserServiceCreateForm;

import java.util.List;

public interface IUserServicesService {

    public List<UserServices> getListUserServices();

    public void createUserServices(UserServiceCreateForm form);

    public void deleteUserServices(String hotelServicesName,int bookingId);
}
