package com.nixer.nprox.controller;

import com.nixer.nprox.entity.NUser;
import com.nixer.nprox.service.NUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (NUser)表控制层
 *
 * @author makejava
 * @since 2021-06-07 16:45:31
 */
@RestController
@RequestMapping("nUser")
public class NUserController {
    /**
     * 服务对象
     */
    @Resource
    private NUserService nUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public NUser selectOne(Integer id) {
        return this.nUserService.queryById(id);
    }


    @PostMapping("addUser")
    public  NUser addUser(@RequestBody NUser nUser){
        this.nUserService.insert(nUser);
        return nUser;
    }


    @PutMapping("/updatePwd")
    public int updatePwd(@RequestBody Map<String, String> map){
        return nUserService.updatePwd(map.get("oldPwd"), map.get("newPwd"));
    }


}
