package com.nixer.nprox.service.impl;

import com.nixer.nprox.dao.*;
import com.nixer.nprox.entity.*;
import com.nixer.nprox.entity.swarm.Agent;
import com.nixer.nprox.entity.SwarmUserNodesNum;
import com.nixer.nprox.entity.swarm.dto.*;
import com.nixer.nprox.service.AgentNodeService;
import com.nixer.nprox.service.AgentService;
import com.nixer.nprox.tools.ResultCode;
import com.nixer.nprox.tools.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AgentNodeServiceImpl implements AgentNodeService {

    @Autowired
    private AgentDao agentDao;

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentNodesDao agentNodesDao;

    @Autowired
    private SwarmUserNodesDao swarmUserNodesDao;

    @Autowired
    private AgentUserNodesNumDao agentUserNodesNumDao;

    @Autowired
    private SwarmUserDayDao   swarmUserDayDao;

    @Autowired
    private SwarmUserTotalDao  swarmUserTotalDao;

    @Autowired
    private SwarmUserNodesNumDao swarmUserNodesNumDao;

    @Autowired
    private AgentUserDao  agentUserDao;


    @Override
    @Transactional(rollbackFor = Exception.class)//TODO 事务没有回滚
    public ResultJson<AgentPoolState> distributionNodes(UnodesDtoExt unodesDtoExt, long userid) throws ParseException {
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        AgentPoolState agentPoolState = agentService.agentPoolState(userid);
        if(agentPoolState==null||agentPoolState.getTotal_num()==0){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"分配节点数量不足");
        }
        int nodestate = agentNodesDao.findNodesBindStatus(unodesDtoExt);
        if(nodestate>0){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"分配节点异常");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(unodesDtoExt.getSovertime());

        if(d.getTime()<=new Date().getTime()){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"时间错误");
        }

        //更新user节点状态
        AgentUserNodesNum aAgentUserNodesNum = agentUserNodesNumDao.findByUserid(agent.getId(),
                String.valueOf(unodesDtoExt.getUserid()));
        if(aAgentUserNodesNum==null){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"用户状态未知");
        }

        SwarmUserDay agentSwarmUserDay = swarmUserDayDao.findByUserid(userid,sdf.format(new Date()));
        SwarmUserDay userSwarmUserDay = swarmUserDayDao.findByUserid(unodesDtoExt.getUserid(), sdf.format(new Date()));

        SwarmUserNodesNum agentSwarmUserNodesNum = swarmUserNodesNumDao.findByUserId(userid);
        SwarmUserNodesNum userSwarmUserNodesNum = swarmUserNodesNumDao.findByUserId(unodesDtoExt.getUserid());

        SwarmUserTotal  agentSwarmUserTotal =  swarmUserTotalDao.findByUserId(userid);
        SwarmUserTotal  userSwarmUserTotal =  swarmUserTotalDao.findByUserId(unodesDtoExt.getUserid());

        if(agentSwarmUserDay==null||userSwarmUserDay==null||agentSwarmUserNodesNum==null||userSwarmUserNodesNum==null||agentSwarmUserTotal==null||userSwarmUserTotal==null){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"用户状态未知");
        }

        AgentLevel agentLevel =agentService.creatUserAgentLevel(agent.getId(),unodesDtoExt.getNodesUidDtoList().size());

        if(agentLevel==null){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"无法分配等级");
        }

        //代理商节点下去
        swarmUserNodesDao.deleteUserNodes(userid,unodesDtoExt.getNodesUidDtoList());

        List<SwarmUserNodes> agentNodesList = new ArrayList<>();
        for(NodesUidDto nodesUidDto:unodesDtoExt.getNodesUidDtoList()){
            SwarmUserNodes swarmUserNodes= new SwarmUserNodes();
            swarmUserNodes.setNodeUid(nodesUidDto.getNode_uid());
            swarmUserNodes.setUserid(unodesDtoExt.getUserid());
            swarmUserNodes.setOvertime(d);
            agentNodesList.add(swarmUserNodes);
        }
        //新增用户节点
        swarmUserNodesDao.insertBatch(agentNodesList);

        agentUserDao.updateUserLevelAndCharge(agentLevel,unodesDtoExt.getUserid());

        //更新代理商节点
        agentNodesDao.updateAgentNodes(agent.getId(),unodesDtoExt.getNodesUidDtoList(),1);

        int updatenodes =   unodesDtoExt.getNodesUidDtoList().size();

        agentUserNodesNumDao.updateAgentUserNodesNum(unodesDtoExt.getUserid(),agent.getId(),updatenodes);



        swarmUserDayDao.updateNodeNumByUserid(userid,sdf.format(new Date()),-updatenodes);
        swarmUserDayDao.updateNodeNumByUserid(unodesDtoExt.getUserid(),sdf.format(new Date()),updatenodes);

        swarmUserNodesNumDao.updateNodeNumByUserid(userid,-updatenodes);
        swarmUserNodesNumDao.updateNodeNumByUserid(unodesDtoExt.getUserid(),updatenodes);

        swarmUserTotalDao.updateNodeNumByUserid(userid,-updatenodes);
        swarmUserTotalDao.updateNodeNumByUserid(unodesDtoExt.getUserid(),updatenodes);

        AgentPoolState newagentPoolState = agentService.agentPoolState(userid);

        return ResultJson.ok(newagentPoolState);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson<AgentPoolState> batchDistributionNodes(long userid, UnodeBetchExt unodeBetchExt) throws ParseException {
        //找到数量个直接调用其他方法
        List<NodesUidDto> nodesUidDtoList = new ArrayList<>();
        List<AgentNodes> agentNodesList = agentNodesDao.findUnUseNodesLimit(unodeBetchExt.getNodesnum());
         if(agentNodesList.size()!=unodeBetchExt.getNodesnum()){
             return ResultJson.failure(ResultCode.BAD_REQUEST,"无法分配节点");
         }
        for (AgentNodes agentNodes:agentNodesList) {
            NodesUidDto nodesUidDto = new NodesUidDto();
            nodesUidDto.setNode_uid(agentNodes.getNode_uid());
            nodesUidDtoList.add(nodesUidDto);
        }
        UnodesDtoExt unodesDtoExt = new UnodesDtoExt();
        unodesDtoExt.setSovertime(unodeBetchExt.getSovertime());
        unodesDtoExt.setUserid(unodeBetchExt.getUserid());
        unodesDtoExt.setNodesUidDtoList(nodesUidDtoList);
        return distributionNodes(unodesDtoExt,userid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson<AgentPoolState> deallocationNodes(UnodesDtoExt unodesDtoExt, long userid) throws ParseException {
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(unodesDtoExt.getSovertime());

        //更新user节点状态
        AgentUserNodesNum aAgentUserNodesNum = agentUserNodesNumDao.findByUserid(agent.getId(),
                String.valueOf(unodesDtoExt.getUserid()));
        if(aAgentUserNodesNum==null){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"用户状态未知");
        }

        SwarmUserDay agentSwarmUserDay = swarmUserDayDao.findByUserid(userid,sdf.format(new Date()));
        SwarmUserDay userSwarmUserDay = swarmUserDayDao.findByUserid(unodesDtoExt.getUserid(), sdf.format(new Date()));
        SwarmUserNodesNum agentSwarmUserNodesNum = swarmUserNodesNumDao.findByUserId(userid);
        SwarmUserNodesNum userSwarmUserNodesNum = swarmUserNodesNumDao.findByUserId(unodesDtoExt.getUserid());
        SwarmUserTotal  agentSwarmUserTotal =  swarmUserTotalDao.findByUserId(userid);
        SwarmUserTotal  userSwarmUserTotal =  swarmUserTotalDao.findByUserId(unodesDtoExt.getUserid());

        if(agentSwarmUserDay==null||userSwarmUserDay==null||agentSwarmUserNodesNum==null||userSwarmUserNodesNum==null||agentSwarmUserTotal==null||userSwarmUserTotal==null){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"用户状态未知");
        }

        AgentLevel agentLevel =agentService.creatUserAgentLevel(agent.getId(),
                (aAgentUserNodesNum.getNode_num()-unodesDtoExt.getNodesUidDtoList().size()));

        if(agentLevel==null){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"无法分配等级");
        }

        //用户节点下去
        swarmUserNodesDao.deleteUserNodes(unodesDtoExt.getUserid(),unodesDtoExt.getNodesUidDtoList());



        List<SwarmUserNodes> agentNodesList = new ArrayList<>();
        for(NodesUidDto nodesUidDto:unodesDtoExt.getNodesUidDtoList()){
            SwarmUserNodes swarmUserNodes= new SwarmUserNodes();
            swarmUserNodes.setNodeUid(nodesUidDto.getNode_uid());
            swarmUserNodes.setUserid((int)userid);
            swarmUserNodes.setOvertime(null);
            agentNodesList.add(swarmUserNodes);
        }
        //新增代理商节点
        swarmUserNodesDao.insertBatch(agentNodesList);



        agentUserDao.updateUserLevelAndCharge(agentLevel,unodesDtoExt.getUserid());

        //更新代理商节点
        agentNodesDao.updateAgentNodes(agent.getId(),unodesDtoExt.getNodesUidDtoList(),0);

        //旧节点数量
        int oldusernode = aAgentUserNodesNum.getNode_num();

        aAgentUserNodesNum.setNode_num(oldusernode-unodesDtoExt.getNodesUidDtoList().size());

        int updatenodes =   unodesDtoExt.getNodesUidDtoList().size();

        agentUserNodesNumDao.updateAgentUserNodesNum(unodesDtoExt.getUserid(),agent.getId(),-updatenodes);


        swarmUserDayDao.updateNodeNumByUserid(userid,sdf.format(new Date()),updatenodes);
        swarmUserDayDao.updateNodeNumByUserid(unodesDtoExt.getUserid(),sdf.format(new Date()),-updatenodes);

        swarmUserNodesNumDao.updateNodeNumByUserid(userid,updatenodes);
        swarmUserNodesNumDao.updateNodeNumByUserid(unodesDtoExt.getUserid(),-updatenodes);

        swarmUserTotalDao.updateNodeNumByUserid(userid,updatenodes);
        swarmUserTotalDao.updateNodeNumByUserid(unodesDtoExt.getUserid(),-updatenodes);


        AgentPoolState newagentPoolState = agentService.agentPoolState(userid);

        return ResultJson.ok(newagentPoolState);


    }
}

