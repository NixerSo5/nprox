package com.nixer.nprox.controller.ntest;

import com.alibaba.fastjson.JSONObject;
import com.nixer.nprox.dao.SwarmTokensDao;
import com.nixer.nprox.entity.SwarmTokens;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private SwarmTokensDao swarmTokensDao;



    @RequestMapping("do")
    public String test(){

        List<SwarmTokens> swarmTokens= swarmTokensDao.queryAllByLimit(0,100);

        return JSONObject.toJSONString(swarmTokens);
    }


    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("100000000000000000000000000");
        BigInteger bigInteger1 = new BigInteger("2e30");
        System.out.println(bigInteger1.add(bigInteger));

    }

}
