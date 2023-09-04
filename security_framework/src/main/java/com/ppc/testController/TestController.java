package com.ppc.testController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class TestController {
    @GetMapping("/login")
    @ApiOperation("登录")
    public String login(){
        return "login success";
    }
    @GetMapping("/hello")
    @ApiOperation("hello")
    public String sayHello(){
        return "hello";
    }
}
