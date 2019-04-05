package com.gduf.clock.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.gduf.clock.core.UserInfoDecode;
import com.gduf.clock.entity.UserInfo;
import com.gduf.clock.exception.UserException;
import com.gduf.clock.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private UserInfoMapper userInfoMapper;

    public UserServiceImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo userLogin(String encryptedData, String iv, String code) {
        //解密
        Map map = UserInfoDecode.decode(encryptedData, iv, code);
        String status = map.get("status").toString();
        if (StringUtils.equals(status, "1")) {
            //处理
            UserInfo userInfo = JSONObject.parseObject(JSONObject.toJSONString(map.get("userInfo")).toString(), UserInfo.class);

            UserInfo userTemp = userInfoMapper.selectByPrimaryKey(userInfo);
            if (userTemp != null) {
                //用户信息更改
                if (!StringUtils.equals(JSONObject.toJSONString(userInfo), JSONObject.toJSONString(userTemp))) {
                    userInfoMapper.updateByPrimaryKeySelective(userInfo);
                }
            } else {
                userInfo.setTime(new Date());
                //插入新用户
                userInfoMapper.insert(userInfo);
            }
            return userInfo;
        } else {
            //抛出异常
            throw new UserException("验证失败");
        }
    }
}
