package com.nixer.nprox.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("test")
public class TestController {

    @GetMapping("/")
    @ApiOperation(value = "test", notes = "test")
    public String test() {
        return "success!";
    }

    @GetMapping("/security")
    @ApiOperation(value = "hello", notes = "hello")
    public String security() {
        return "hello springboot security!";
    }
}

