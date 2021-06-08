package com.nixer.nprox.service.swarm.impl;

import com.nixer.nprox.service.swarm.SwarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class SwarmServiceImpl implements SwarmService {

        @Autowired
        MongoTemplate mongoTemplate;
//      mongoTemplate.save(info);
//      return mongoTemplate.findOne(query, UserLoginInfo.class);
//      mongoTemplate.updateMulti(query, update, UserLoginInfo.class);
//      return mongoTemplate.findAndRemove(query, UserLoginInfo.class);

}
