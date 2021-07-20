package com.nixer.nprox.controller.api;


import com.alibaba.fastjson.JSONObject;
import com.nixer.nprox.entity.swarm.dto.SwarmDayDto;
import com.nixer.nprox.entity.swarm.pool.PoolConfig;
import com.nixer.nprox.tools.RedisUtil;
import com.nixer.nprox.tools.ResultCode;
import com.nixer.nprox.tools.ResultJson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.datasource.pooled.PoolState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api")
public class ApiController {


    @Autowired
    public RedisUtil redisUtil;


    @GetMapping(value = "/poolState")
    @ApiOperation(value = "矿池状态", notes = "展示矿池状态目前的节点数 gas费用和bzz价格")
    public ResultJson<PoolConfig> poolState(){
        String  poolConfigStr = redisUtil.get("POOL:STATE");
        PoolConfig poolConfig = JSONObject.parseObject(poolConfigStr,PoolConfig.class);
        return ResultJson.ok(poolConfig);
    }

    @GetMapping(value = "/poolDay")
    @ApiOperation(value = "矿池日产(首页)", notes = "展示矿池日常状态")
    public ResultJson<SwarmDayDto> poolDay(){
        String  swarmDayDtoStr = redisUtil.get("POOL:DAY");
        SwarmDayDto swarmDayDto = JSONObject.parseObject(swarmDayDtoStr,SwarmDayDto.class);
        return ResultJson.ok(swarmDayDto);
    }





}
