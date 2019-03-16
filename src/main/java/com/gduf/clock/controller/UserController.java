package com.gduf.clock.controller;


import com.alibaba.fastjson.JSONObject;
import com.gduf.clock.core.UserInfoDecode;
import com.gduf.clock.service.UserService;
import com.gduf.clock.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserServiceImpl userService)
    {
        this.userService=userService;
    }
    @RequestMapping(value="login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public void login(@RequestBody JSONObject jsonParam){
        String encryptedData=jsonParam.getString("encryptedData");
        String iv=jsonParam.getString("iv");
        String code=jsonParam.getString("code");

        userService.userLogin(encryptedData,iv,code);

    }

}
