package com.gduf.clock.service.impl;

import com.gduf.clock.service.CommentService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created with IDEA
 * author:HaoChen
 * Date:2019/3/23
 * Time:16:53
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Value("${web.upload.path}")
    private String uploadPath;
    @Override
    public void uploadImage(MultipartFile[] files, String commentToImage) {
        upload(files);
    }

    @Override
    public void uploadVideo(MultipartFile[] files, String commentToImage) {

    }

    @Override
    public void uploadSpeech(MultipartFile[] files, String commentToImage) {

    }


    public void upload(MultipartFile[] files)
    {
        //多文件上传
        if(files!=null && files.length>=1) {
            BufferedOutputStream bw = null;

            try {
                for( MultipartFile file : files)
                {
                    String fileName = file.getOriginalFilename();

                    //判断是否有文件(实际生产中要判断是否是音频文件)
                    if(StringUtil.isNotEmpty(fileName)) {
                        //创建输出文件对象
                        File outFile = new File(uploadPath+ fileName);
                        //拷贝文件到输出文件对象
                        FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(bw!=null) {bw.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
