package com.gduf.clock.controller;


import com.alibaba.fastjson.JSONObject;
import com.gduf.clock.service.UserService;
import com.gduf.clock.service.impl.UserServiceImpl;
import com.gduf.clock.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> login(@RequestBody JSONObject jsonParam) {
        String encryptedData = jsonParam.getString("encryptedData");
        String iv = jsonParam.getString("iv");
        String code = jsonParam.getString("code");
        Result result;
        try {
            userService.userLogin(encryptedData, iv, code);
            result = new Result(200, "success");
        } catch (Exception e) {
            result = new Result(500, "error");
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
