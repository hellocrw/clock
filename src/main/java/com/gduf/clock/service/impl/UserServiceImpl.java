package com.gduf.clock.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.gduf.clock.core.UserInfoDecode;
import com.gduf.clock.dao.UserInfoMapper;
import com.gduf.clock.entity.UserInfo;
import com.gduf.clock.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
@Service
public class UserServiceImpl implements UserService {
    private UserInfoMapper userInfoMapper;
    private UserServiceImpl(UserInfoMapper userInfoMapper)
    {
        this.userInfoMapper=userInfoMapper;
    }
    @Override
    @Transactional
    public void userLogin(String encryptedData, String iv, String code) {
        //解密
        Map map = UserInfoDecode.decode(encryptedData,iv,code);
        String status=map.get("status").toString();
        if (StringUtils.equals(status,"1"))
        {
            //处理
            UserInfo userInfo= JSONObject.parseObject(JSONObject.toJSONString(map.get("userInfo")).toString(),UserInfo.class);

            UserInfo userTemp=userInfoMapper.selectByPrimaryKey(userInfo);
            if(userTemp!=null)
            {
                //用户信息更改
                if(!StringUtils.equals(JSONObject.toJSONString(userInfo),JSONObject.toJSONString(userTemp)))
                {
                    userInfoMapper.updateByPrimaryKeySelective(userInfo);
                }
            }
            else
            {
                //插入新用户
                userInfoMapper.insert(userInfo);
            }
        }
        else
        {
            //抛出异常
        }
    }
}
