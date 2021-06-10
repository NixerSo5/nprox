package com.nixer.nprox.service.swarm.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nixer.nprox.dao.SwarmDao;
import com.nixer.nprox.entity.swarm.PoolNodes;
import com.nixer.nprox.entity.swarm.*;
import com.nixer.nprox.entity.swarm.dto.*;
import com.nixer.nprox.entity.swarm.pool.PoolConfig;
import com.nixer.nprox.service.swarm.SwarmService;
import com.nixer.nprox.tools.RedisUtil;
import com.nixer.nprox.tools.ResultCode;
import com.nixer.nprox.tools.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SwarmServiceImpl implements SwarmService {

       @Autowired
       RedisUtil redisUtil;

        @Autowired
        MongoTemplate mongoTemplate;

        @Autowired
        SwarmDao swarmDao;

    @Override
    public SwarmDayDto getSwarmDay() {
        //TODO  没有计算当日矿池状态的线程
        List<SwarmDay> swarmDayList = swarmDao.getSwarmDayLastSevenDay();
        int daysize = swarmDayList.size();
        BigDecimal totalbzz = new BigDecimal(0);
        double totalcash_out = 0;
        if(daysize > 0){
            for(SwarmDay swarmDay:swarmDayList){
                totalcash_out += swarmDay.getCashout();
                totalbzz = totalbzz.add(swarmDay.getBzzout());
            }

            String  poolConfigStr = redisUtil.get("POOL:STATE");
            PoolConfig poolConfig = JSONObject.parseObject(poolConfigStr,PoolConfig.class);

            SwarmDayDto swarmDayDto =new SwarmDayDto();
            swarmDayDto.setBzz(poolConfig.getBzz());


            int nodenum = Integer.valueOf(poolConfig.getNode_num());
            swarmDayDto.setNode_num(poolConfig.getNode_num());


            swarmDayDto.setBzz_day(String.valueOf(totalbzz.divide(new BigDecimal(daysize),16, BigDecimal.ROUND_HALF_UP)));
            swarmDayDto.setCashout_day(String.valueOf((int)Math.ceil(totalcash_out/daysize)));
            swarmDayDto.setSingle_node_cashout(String.valueOf(new BigDecimal(totalcash_out/nodenum).divide(new BigDecimal(1),8, BigDecimal.ROUND_HALF_UP)));
            swarmDayDto.setSingle_node_profit(String.valueOf(totalbzz.divide(new BigDecimal(nodenum),16, BigDecimal.ROUND_HALF_UP)));
            return swarmDayDto;
        }
        return null;
    }

    @Override
    public UserPoolUnit userPoolState(long userid) {
        UserPoolUnit userPoolUnit = new UserPoolUnit();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        Date start = c.getTime();
        String beforday= sdf.format(start);//前一天

        //用户总计
        SwarmUserTotalExt swarmUserTotal = swarmDao.getSwarmUserTotal(userid);
        userPoolUnit.setSwarmUserTotal(swarmUserTotal);

        String nodesnum = redisUtil.get("POOL:NODES");

        //用户当日 TODO 如果用户当日没有数据那么新建用户并计算当日数据 数据按道理应该交给其他计算线程而非此处手动建立 可以在前一天预置后一天
        SwarmUserDayExt  swarmUserDay = swarmDao.getSwarmUserDay(userid,sdf.format(new Date()));
        if(swarmUserDay==null){
            SwarmUserDayExt  oldswarmUserDay = swarmDao.getSwarmUserDay(userid,beforday);
            swarmUserDay = new SwarmUserDayExt();
            swarmUserDay.setBzz(new BigDecimal(0));
            swarmUserDay.setNode_num(oldswarmUserDay.getNode_num());
            swarmUserDay.setCash_out(0);
        }
        userPoolUnit.setSwarmUserDay(swarmUserDay);

        //TODO 计算总体日数据
        SwarmDay swarmDay = swarmDao.getSwarmDay(sdf.format(new Date()));
        if(swarmDay==null){
            swarmDay = new SwarmDay();
            swarmDay.setBzzout(new BigDecimal(0));
            swarmDay.setCashout(0);
            swarmDay.setNodes_num(Integer.valueOf(nodesnum));
            swarmDay.setUpdate_date(sdf.format(new Date()));
        }

        UserNodesStateDto userNodesStateDto = this.userNodesState(userid);
        userPoolUnit.setUserNodesStateDto(userNodesStateDto);

        BigDecimal userNodeNum = new BigDecimal(userNodesStateDto.getUser_total_num());
        //计算值
        SwarmUserExp swarmUserExp = new SwarmUserExp();
        swarmUserExp.setUser_day_bzz(swarmDay.getBzzout().divide(new BigDecimal(swarmDay.getNodes_num()),16,BigDecimal.ROUND_HALF_UP).multiply(userNodeNum));
        swarmUserExp.setUser_day_cashout(new BigDecimal(swarmDay.getCashout()).divide(new BigDecimal(swarmDay.getNodes_num()),16,BigDecimal.ROUND_HALF_UP).multiply(userNodeNum));
        userPoolUnit.setSwarmUserExp(swarmUserExp);


        return userPoolUnit;
    }

    @Override
    public UserNodesStateDto userNodesState(long userid) {
        List<PoolNodes> poolNodesList = swarmDao.getUserNodesState(userid);
        UserNodesStateDto userNodesStateDto = new UserNodesStateDto();
        String  swar = redisUtil.get("POOL:STATE");
        PoolConfig poolConfig = JSONObject.parseObject(swar,PoolConfig.class);
        userNodesStateDto.setServer_total_num(poolConfig.getNode_num());
        int userTotal = 0;
        int active = 0;
        int offline = 0;
        for(PoolNodes poolNodes:poolNodesList){
           if(poolNodes.getState() == 0){
               offline+=poolNodes.getNum();
           }else {
               active+=poolNodes.getNum();
           }
        }
        userTotal = active + offline;
        userNodesStateDto.setOffline_nodes_num(String.valueOf(offline));
        userNodesStateDto.setActive_nodes_num(String.valueOf(active));
        userNodesStateDto.setUser_total_num(String.valueOf(userTotal));
        return userNodesStateDto;
    }

    @Override
    public List<LineDateDto> userPoolStateLine(long userid) {
        List<UserSwarmLine> userSwarmLines = swarmDao.userPoolStateLine(userid);
        List<LineDateDto> lineDateDtoList = new ArrayList<>();
        //TODO 0没有做处理
        for(UserSwarmLine userSwarmLine:userSwarmLines){
            BigDecimal single_node_cashout = new BigDecimal(userSwarmLine.getPool_cashout()).divide(new BigDecimal(userSwarmLine.getPool_nodes_num()),8, BigDecimal.ROUND_HALF_UP);
            BigDecimal single_node_bzz = userSwarmLine.getPool_bzz().divide(new BigDecimal(userSwarmLine.getUser_nodes_num()),8, BigDecimal.ROUND_HALF_UP);
            LineDateDto lineDateDto = new LineDateDto();
            lineDateDto.setDate(userSwarmLine.getDate());
            lineDateDto.setCashout(String.valueOf(single_node_cashout.multiply(new BigDecimal(userSwarmLine.getUser_nodes_num()))));
            lineDateDto.setCheques(String.valueOf(single_node_bzz.multiply(new BigDecimal(userSwarmLine.getUser_nodes_num()))));
            lineDateDtoList.add(lineDateDto);
        }
        return lineDateDtoList;
    }

    @Override
    public PageInfo<SwarmNodes> useNodesList(NodesFindDto nodesFindDto, long userid) {
        PageHelper.startPage(nodesFindDto.getIndex(), nodesFindDto.getSize());
        List<SwarmNodes> lists = swarmDao.useNodesList(userid,nodesFindDto.getState());
        PageInfo<SwarmNodes> pageInfo = new PageInfo<SwarmNodes>(lists);
        return pageInfo;
    }

    @Override
    public ResultJson useNodesAdd(UserNodeUpdateDto userNodeUpdateDto, long userid) {
        //查询是否存在该节点该节点是否存在关系
        int num = swarmDao.findNodeByAddress(userNodeUpdateDto.getAddress());
        if(num<=0){
            return ResultJson.failure("节点不存在");
        }else{
            SwarmUserNode swarmUserNode = swarmDao.findNodeByAddressCanUse(userNodeUpdateDto.getAddress());
            if(swarmUserNode!=null){
                return ResultJson.failure("节点被锁定");
            }else{
                swarmUserNode =new SwarmUserNode();
                swarmUserNode.setNode_address(userNodeUpdateDto.getAddress());
                swarmUserNode.setUserid((int) userid);
                swarmDao.saveUserNode(swarmUserNode);
                if(swarmUserNode.getId()>0){
                    return ResultJson.ok();
                }else{
                    return  ResultJson.failure("保存失败");
                }
            }
        }
    }

    @Override
    public ResultJson useNodesDel(UserNodeUpdateDto userNodeUpdateDto, long userid) {
        int i = swarmDao.delUserNode(userid,userNodeUpdateDto.getAddress());
        return ResultJson.ok();
    }

    @Override
    public int getNodesNum() {
        return this.swarmDao.getNodesNum();
    }


    public static void main(String[] args) {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        Date start = c.getTime();
        String beforday= sdf.format(start);//前一天
        System.out.println(beforday);
    }
//      mongoTemplate.save(info);
//      return mongoTemplate.findOne(query, UserLoginInfo.class);
//      mongoTemplate.updateMulti(query, update, UserLoginInfo.class);
//      return mongoTemplate.findAndRemove(query, UserLoginInfo.class);




}
