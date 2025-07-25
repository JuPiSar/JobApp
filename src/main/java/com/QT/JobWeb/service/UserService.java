package com.QT.JobWeb.service;

import com.QT.JobWeb.dto.RegistrationDto;
import com.QT.JobWeb.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
