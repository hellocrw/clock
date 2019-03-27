package com.gduf.clock.core;

import com.alibaba.fastjson.JSONObject;

import com.gduf.clock.util.AesCbcUtil;
import com.gduf.clock.util.Request;
import org.apache.http.client.methods.CloseableHttpResponse;


import java.util.HashMap;
import java.util.Map;

public class UserInfoDecode {

    /**
     * 小程序唯一标识
     */
    static private final String WXSP_APPID = "wxbeabb4c22c76db5f";
    /**
     * 小程序的 app secret
     */
    static private  final String WXSP_SECRET = "6e9c535d608be6f2cd1288196e8ca54e";
    /**
     * 授权（必填）
     */
    static private  final String GRANT_TYPE = "authorization_code";

    public static Map decode(String encryptedData, String iv, String code) {

        Map map = new HashMap(16);
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }

        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + WXSP_APPID + "&secret=" + WXSP_SECRET + "&js_code=" + code + "&GRANT_TYPE=" + GRANT_TYPE;
        //发送请求
         CloseableHttpResponse response = Request.get("https://api.weixin.qq.com/sns/jscode2session?"+params);
         String sr=Request.getContent(response);
         //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //获取会话密钥（session_key）
        String sessionKey = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", "1");
                map.put("msg", "解密成功");
                JSONObject userInfoJSON = JSONObject.parseObject(result);
                Map userInfo = new HashMap(16);
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", "0");
        map.put("msg", "解密失败");
        return map;
    }
}
