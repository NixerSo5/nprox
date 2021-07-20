package com.nixer.nprox.thread;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.nixer.nprox.entity.swarm.pool.PoolConfig;
import com.nixer.nprox.service.swarm.SwarmService;
import com.nixer.nprox.tools.HttpUtil;
import com.nixer.nprox.tools.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class GetHonnyPoolState {

    private static final String ETHURL = "https://api.etherscan.io/api?module=gastracker&action=gasoracle&apikey=EUX7AC57C1Q3H3VR5QQNYKT76UDH4U9738";


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SwarmService service;


    @Async
    public void HonnyPoolStateThreadRun() {
        //TODO 这个应该放入循环中
        int nodeNum = service.getNodesNum();
        nodeNum = 22;
        redisUtil.set("POOL:NODES",String.valueOf(nodeNum));
        while (true) {
            try {
                PoolConfig poolConfig = new PoolConfig();
                String nodeNumx = redisUtil.get("POOL:NODES");
                poolConfig.setNode_num(nodeNumx);
                String bzz = "--";
                poolConfig.setBzz(bzz);
                poolConfig.setGas("--");
                poolConfig.setGasmin("--");
                //{"status":"1","message":"OK","result":{"LastBlock":"12593044","SafeGasPrice":"11","ProposeGasPrice":"15","FastGasPrice":"16"}}
               // String res = HttpUtil.doGet(ETHURL);
                String res =null;
                if(res!=null&&!res.equals("")){
                    JSONObject jso = JSONObject.parseObject(res);
                    if(jso.getString("status").equals("1")){
                        poolConfig.setGas(jso.getJSONObject("result").getString("ProposeGasPrice"));
                        poolConfig.setGasmin(jso.getJSONObject("result").getString("SafeGasPrice"));
                    }
                }
                Gson gson = new Gson();
                redisUtil.set("POOL:STATE", gson.toJson(poolConfig));
                Thread.sleep(4 * 1000);
            } catch (Exception e) {
                // HttpUtil.postAsyncHttpClient("http://127.0.0.1:8080/actuator/shutdown",null,null);
                e.printStackTrace();
            }
        }
    }
}
