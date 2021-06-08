package com.nixer.nprox.controller.swarm;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swarm")
public class SwarmController {

    @PreAuthorize("hasAnyRole('USER')") // 只能user角色才能访问该方法
    @GetMapping("/user")
    public String user(){
        return "user角色访问";
    }

    @PreAuthorize("hasAnyRole('ADMIN')") // 只能admin角色才能访问该方法
    @GetMapping("/admin")
    public String admin(){

        return "admin角色访问";
    }
}
