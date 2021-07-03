package com.nixer.nprox.thread;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.nixer.nprox.entity.swarm.dto.SwarmDayDto;
import com.nixer.nprox.entity.swarm.pool.PoolConfig;
import com.nixer.nprox.service.swarm.SwarmService;
import com.nixer.nprox.tools.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class GetHonnyPoolDay {


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SwarmService service;


    @Async
    public void HonnyPoolDayThreadRun() {
        while (true) {
            try {
                //查询出日结果然后对日结果进行计算
                SwarmDayDto swarmDayDto = service.getSwarmDay();
                if(swarmDayDto!=null){
                    Gson gson = new Gson();
                    redisUtil.set("POOL:DAY", gson.toJson(swarmDayDto));
                    Thread.sleep(60 * 1000 * 60 * 1);
                }else{
                    Thread.sleep(60 * 1000 * 30);
                }
            } catch (Exception e) {
                // HttpUtil.postAsyncHttpClient("http://127.0.0.1:8080/actuator/shutdown",null,null);
                e.printStackTrace();
            }
        }
    }
}
