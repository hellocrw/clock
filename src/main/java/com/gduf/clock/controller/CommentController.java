package com.gduf.clock.controller;





import com.gduf.clock.service.CommentService;
import com.gduf.clock.service.impl.CommentServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentServiceImpl commentService)
    {
        this.commentService=commentService;

    }
    @RequestMapping(value = "uploadImage")
    public void uploadImage(@RequestParam("file") MultipartFile[] files,
                          @RequestParam("commentMap") String commentMap)
    {
        commentService.uploadImage(files,commentMap);
    }
}
