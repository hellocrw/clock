package com.gduf.clock.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public Object sayHello()
    {
        return "hello";
    }
}
