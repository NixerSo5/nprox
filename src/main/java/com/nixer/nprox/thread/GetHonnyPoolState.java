package com.nixer.nprox.thread;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.nixer.nprox.entity.swarm.pool.PoolConfig;
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


    @Async
    public void HonnyPoolStateThreadRun() {
        while (true) {
            try {
                //TODO 目前没有获取节点数量的接口
                PoolConfig poolConfig = new PoolConfig();
                String nodeNum = "1500";
                poolConfig.setNode_num(nodeNum);
                String bzz = "1845.13";
                poolConfig.setBzz(bzz);
                poolConfig.setGas("147.1");
                //{"status":"1","message":"OK","result":{"LastBlock":"12593044","SafeGasPrice":"11","ProposeGasPrice":"15","FastGasPrice":"16"}}
                String res = HttpUtil.doGetProxyOne(ETHURL);
                if(res!=null&&!res.equals("")){
                    JSONObject jso = JSONObject.parseObject(res);
                    if(jso.getString("status").equals("1")){
                        poolConfig.setGas(jso.getJSONObject("result").getString("ProposeGasPrice"));
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
