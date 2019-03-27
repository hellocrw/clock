package com.gduf.clock.controller;

import com.gduf.clock.entity.UserInfo;
import com.gduf.clock.service.UserService;
import com.gduf.clock.service.impl.UserServiceImpl;
import com.gduf.clock.vo.Result;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import java.util.HashMap;
@Slf4j
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping(value = "login",produces = "application/json")
    @ApiOperation(value="微信用户登陆", notes="微信用户登陆")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name="encryptedData",dataType = "String",paramType = "query"),
                    @ApiImplicitParam(name="iv",dataType = "String",paramType = "query"),
                    @ApiImplicitParam(name="code",dataType = "String",paramType = "query")
            }
    )
    public ResponseEntity<String> login(String encryptedData,String iv,String code) {
        Result result;
        HashMap jsonData=new HashMap(16);
        if(StringUtil.isEmpty(encryptedData)||StringUtil.isEmpty(iv)||StringUtil.isEmpty(code))
        {
            return new ResponseEntity(new Result("参数不能为空"),HttpStatus.NO_CONTENT);
        }

        try {
            UserInfo userInfo=userService.userLogin(encryptedData, iv, code);
            jsonData.put("openid", userInfo.getOpenId());
            result = new Result(200, "success",jsonData);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity(new Result("服务器错误"),HttpStatus.FAILED_DEPENDENCY);
        }



    }

}
