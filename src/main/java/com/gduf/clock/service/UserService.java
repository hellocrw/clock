package com.gduf.clock.service;

import com.gduf.clock.entity.UserInfo;

public interface UserService {
    UserInfo userLogin(String encryptedData, String iv, String code);
}
