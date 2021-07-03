package com.nixer.nprox.service;

import com.nixer.nprox.entity.swarm.dto.AgentPoolState;
import com.nixer.nprox.entity.swarm.dto.UnodeBetchExt;
import com.nixer.nprox.entity.swarm.dto.UnodesDtoExt;
import com.nixer.nprox.tools.ResultJson;

import java.text.ParseException;

public interface AgentNodeService {
    ResultJson<AgentPoolState> distributionNodes(UnodesDtoExt unodesDtoExt, long userid) throws ParseException;

    ResultJson<AgentPoolState> batchDistributionNodes(long userid, UnodeBetchExt unodeBetchExt) throws ParseException;

    ResultJson<AgentPoolState> deallocationNodes(UnodesDtoExt unodesDtoExt, long userid) throws ParseException;
}
