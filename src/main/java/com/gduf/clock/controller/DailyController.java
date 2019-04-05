package com.gduf.clock.controller;


import com.gduf.clock.service.DailyService;
import com.gduf.clock.service.impl.DailyServiceImpl;
import com.gduf.clock.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;


@RestController
@Slf4j
public class DailyController {

    private DailyService dailyService;

    public DailyController(DailyServiceImpl dailyService) {
        this.dailyService = dailyService;

    }
    @Value("${web.upload.image.type}")
    private int imageType;
    @Value("${web.upload.video.type}")
    private int videoType;
    @Value("${web.upload.speech.type}")
    private int speechType;

    @ApiOperation(value = "发表日志的附件上传", notes = "发表日志的附件上传")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "file", dataType = "String", paramType = "query", value = "文件"),
                    @ApiImplicitParam(name = "dailyMap", dataType = "String", paramType = "query", value = "与日志信息对应关系"),
                    @ApiImplicitParam(name = "openId", dataType = "String", paramType = "query", value = "openId"),
                    @ApiImplicitParam(name = "type", dataType = "int", paramType = "query", value = "1:图片;2:语音;3:视频")
            }
    )
    @PostMapping(value = "upload", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity upload(@RequestParam("file") MultipartFile[] files,
                                 @RequestParam("dailyMap") String dailyMap,
                                 @RequestParam("openId") String openId,
                                 @RequestParam("type") int type) {
        try {
            if (type == imageType) {
                dailyService.uploadImage(files, dailyMap, openId);
            } else if (type == videoType) {
                dailyService.uploadSpeech(files, dailyMap, openId);
            } else if (type == speechType) {
                dailyService.uploadVideo(files, dailyMap, openId);
            }

        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity(new Result("上传失败"), HttpStatus.FAILED_DEPENDENCY);
        }

        Result result = new Result(200, "success");
        return new ResponseEntity(result, HttpStatus.OK);

    }


    @ApiOperation(value = "提交日志内容", notes = "提交日志内容")
    @ApiImplicitParams(
            {
                   @ApiImplicitParam(name = "openId", dataType = "String", paramType = "query", value = "openId"),
                    @ApiImplicitParam(name = "content", dataType = "String", paramType = "query", value = "日志内容"),
                    @ApiImplicitParam(name = "dailyMap", dataType = "String", paramType = "query", value = "与日志信息对应关系")
            }
    )
    @PostMapping(value = "upContent", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity upContent(@RequestParam("openId")String openId,
                                    @RequestParam("content")String content,
                                    @RequestParam("dailyMap") String dailyMap) {
        try {
            if (StringUtil.isNotEmpty(content) && StringUtil.isNotEmpty(openId)&& StringUtil.isNotEmpty(dailyMap)) {
                dailyService.upContent(openId,dailyMap,content);
            }
        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity(new Result("提交失败"), HttpStatus.FAILED_DEPENDENCY);
        }
        Result result = new Result(200, "success");
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
