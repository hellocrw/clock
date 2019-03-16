package com.gduf.clock.service;

public interface UserService {
    void userLogin(String encryptedData, String iv, String code);
}
