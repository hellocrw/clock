package com.gduf.clock.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IDEA
 * author:HaoChen
 * Date:2019/3/23
 * Time:11:05
 */
public interface DailyService {
    void uploadImage(MultipartFile[] files, String dailyMap, String openId);

    void uploadVideo(MultipartFile[] files, String dailyMap, String openId);

    void uploadSpeech(MultipartFile[] files, String dailyMap, String openId);

    void upContent(String openId,String dailyMap,String content);
}
