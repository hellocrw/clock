package com.gduf.clock.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IDEA
 * author:HaoChen
 * Date:2019/3/23
 * Time:11:05
 */
public interface CommentService {
    void uploadImage(MultipartFile[] files,String commentToImage);
    void uploadVideo(MultipartFile[] files,String commentToImage);
    void uploadSpeech(MultipartFile[] files,String commentToImage);
}
