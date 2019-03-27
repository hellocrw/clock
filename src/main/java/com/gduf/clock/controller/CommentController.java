package com.gduf.clock.controller;


import com.gduf.clock.service.CommentService;
import com.gduf.clock.service.impl.CommentServiceImpl;
import com.gduf.clock.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;

    }


    @ApiOperation(value = "发表日志的附件上传", notes = "发表日志的附件上传")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "file", dataType = "String", paramType = "query",value = "文件"),
                    @ApiImplicitParam(name = "commentMap", dataType = "String", paramType = "query",value = "与评论信息对应关系"),
                    @ApiImplicitParam(name = "openId", dataType = "String", paramType = "query",value = "openId"),
                    @ApiImplicitParam(name = "type", dataType = "int", paramType = "query",value = "1:图片;2:语音;3:视频")
            }
    )
    @PostMapping(value = "upload", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile[] files,
                                      @RequestParam("commentMap") String commentMap,
                                      @RequestParam("openId") String openId,
                                      @RequestParam("type") int type) {
        try {
            if (type == 1) {
                commentService.uploadImage(files, commentMap, openId);
            } else if (type == 2) {
                commentService.uploadSpeech(files, commentMap, openId);
            } else if (type == 3) {
                commentService.uploadVideo(files, commentMap, openId);
            }
            Result result = new Result(200, "success");
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Result("上传失败"), HttpStatus.FAILED_DEPENDENCY);
        }

    }

}
