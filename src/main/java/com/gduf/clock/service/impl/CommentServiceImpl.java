package com.gduf.clock.service.impl;

import com.gduf.clock.dao.ImageInfoMapper;
import com.gduf.clock.dao.SpeechInfoMapper;
import com.gduf.clock.dao.VideoInfoMapper;
import com.gduf.clock.entity.ImageInfo;
import com.gduf.clock.entity.SpeechInfo;
import com.gduf.clock.entity.VideoInfo;
import com.gduf.clock.service.CommentService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IDEA
 * author:HaoChen
 * Date:2019/3/23
 * Time:16:53
 */
@Service
public class CommentServiceImpl implements CommentService {
    ImageInfoMapper imageInfoMapper;
    SpeechInfoMapper speechInfoMapper;
    VideoInfoMapper videoInfoMapper;
    @Value("${web.upload.imgPath}")
    private String imgPath;
    @Value("${web.upload.videoPath}")
    private String videoPath;
    @Value("${web.upload.speechPath}")
    private String speechPath;

    public CommentServiceImpl(ImageInfoMapper imageInfoMapper, VideoInfoMapper videoInfoMapper, SpeechInfoMapper speechInfoMapper) {
        this.imageInfoMapper = imageInfoMapper;
        this.speechInfoMapper = speechInfoMapper;
        this.videoInfoMapper = videoInfoMapper;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void uploadImage(MultipartFile[] files, String commentMap,String openId) {
        //保存图片
        upload(files, imgPath);
        ImageInfo imageInfo = ImageInfo.builder()
                .commentMap(commentMap)
                .id(UUID.randomUUID().toString())
                .openId(openId)
                .time(new Date())
                .build();
        imageInfoMapper.insert(imageInfo);
    }

    @Override
    public void uploadVideo(MultipartFile[] files, String commentMap,String openId) {
        //保存录像
        upload(files, videoPath);
        VideoInfo videoInfo = VideoInfo.builder()
                .commentMap(commentMap)
                .id(UUID.randomUUID().toString())
                .openId(openId)
                .time(new Date())
                .build();
        videoInfoMapper.insert(videoInfo);
    }

    @Override
    public void uploadSpeech(MultipartFile[] files, String commentMap,String openId) {
        //保存语音
        upload(files, speechPath);
        SpeechInfo speechInfo = SpeechInfo.builder()
                .commentMap(commentMap)
                .id(UUID.randomUUID().toString())
                .openId(openId)
                .time(new Date())
                .build();
        speechInfoMapper.insert(speechInfo);
    }


    public void upload(MultipartFile[] files, String uploadPath) {
        //多文件上传
        if (files != null && files.length >= 1) {
            BufferedOutputStream bw = null;

            try {
                for (MultipartFile file : files) {
                    String fileName = file.getOriginalFilename();

                    //判断是否有文件(实际生产中要判断是否是音频文件)
                    if (StringUtil.isNotEmpty(fileName)) {
                        //创建输出文件对象
                        File outFile = new File(uploadPath + fileName);
                        //拷贝文件到输出文件对象
                        FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
