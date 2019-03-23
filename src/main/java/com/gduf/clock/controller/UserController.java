package com.gduf.clock.controller;


import com.alibaba.fastjson.JSONObject;
import com.gduf.clock.entity.UserInfo;
import com.gduf.clock.service.UserService;
import com.gduf.clock.service.impl.UserServiceImpl;
import com.gduf.clock.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="微信用户登陆", notes="微信用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "encryptedData", value = "encryptedData", required = true, dataType = "String"),
            @ApiImplicitParam(name = "iv", value = "iv", required = true, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String")
    })
    public ResponseEntity<String> login(@RequestBody JSONObject jsonParam) {
        String encryptedData = jsonParam.getString("encryptedData");
        String iv = jsonParam.getString("iv");
        String code = jsonParam.getString("code");
        Result result;
        HashMap jsonData=new HashMap(16);

        try {
            UserInfo userInfo=userService.userLogin(encryptedData, iv, code);
            jsonData.put("openid", userInfo.getOpenId());
            result = new Result(200, "success",jsonData);
        } catch (Exception e) {
            result = new Result(500, "error");
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
