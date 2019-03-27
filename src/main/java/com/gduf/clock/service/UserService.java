package com.gduf.clock.service;

import com.gduf.clock.entity.UserInfo;

public interface UserService {
    /**
     *
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    UserInfo userLogin(String encryptedData, String iv, String code);
}
