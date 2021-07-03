package com.nixer.nprox.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nixer.nprox.dao.*;
import com.nixer.nprox.entity.AgentLevel;
import com.nixer.nprox.entity.AgentUser;
import com.nixer.nprox.entity.AgentUserNodesNum;
import com.nixer.nprox.entity.common.dto.SinglePramDto;
import com.nixer.nprox.entity.swarm.Agent;
import com.nixer.nprox.entity.swarm.AgentOrder;
import com.nixer.nprox.entity.swarm.dto.*;
import com.nixer.nprox.service.AgentService;
import com.nixer.nprox.tools.ResultCode;
import com.nixer.nprox.tools.ResultJson;
import com.nixer.nprox.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Agent)表服务实现类
 *
 * @author makejava
 * @since 2021-06-17 22:55:40
 */
@Service("agentService")
public class AgentServiceImpl implements AgentService {
    @Resource
    private AgentDao agentDao;
    @Autowired
    private AgentNodesDao agentNodesDao;
    @Autowired
    private AgentLevelDao agentLevelDao;
    @Autowired
    private AgentUserDao agentUserDao;
    @Autowired
    private AgentUserNodesNumDao agentUserNodesNumDao;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Agent queryById(Integer id) {
        return this.agentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Agent> queryAllByLimit(int offset, int limit) {
        return this.agentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param agent 实例对象
     * @return 实例对象
     */
    @Override
    public Agent insert(Agent agent) {
        this.agentDao.insert(agent);
        return agent;
    }

    /**
     * 修改数据
     *
     * @param agent 实例对象
     * @return 实例对象
     */
    @Override
    public Agent update(Agent agent) {
        this.agentDao.update(agent);
        return this.queryById(agent.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.agentDao.deleteById(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertAgentOrder(AgentOrder agentOrder) {
        return this.agentDao.insertAgentOrder(agentOrder);
    }

    @Override
    public AgentPoolState agentPoolState(long userid) {
        List<NodeNumDto> nodeNumDtoList = agentNodesDao.agentPoolState(userid);
        AgentPoolState agentPoolState = new AgentPoolState();
        int totalnum = 0;
        for (NodeNumDto nodeNumDto : nodeNumDtoList) {
            totalnum += nodeNumDto.getNum();
            if (nodeNumDto.getIsuse() == 1) {
                agentPoolState.setSend_num(nodeNumDto.getNum());
            }
            if (nodeNumDto.getIsuse() == 0) {
                agentPoolState.setRest_num(nodeNumDto.getNum());
            }
        }
        agentPoolState.setTotal_num(totalnum);
        return agentPoolState;
    }

    @Override
    public ResultJson addAgentLevel(long userid, AgentLevelDto agentLevelDto) {

        //查询代理商信息
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        //查看现有等级
        List<AgentLevel> agentLevelList =agentLevelDao.getAgentLevelListByAgentId(agent.getId());
       // int levelindex = agentLevelList.size();
        //查询是否有相同的定义数
        for(AgentLevel agentLevel:agentLevelList){
             if(agentLevel.getNeed_nodes() == agentLevelDto.getNeed_nodes()){
                return ResultJson.failure(ResultCode.BAD_REQUEST,"已设定节点等级");
             }
        }
        AgentLevel agentLevel = new AgentLevel();
        agentLevel.setLevel(agentLevelDto.getLevel());
        agentLevel.setCharges(agentLevelDto.getCharges());
        agentLevel.setNeed_nodes(agentLevelDto.getNeed_nodes());
        agentLevel.setAgentid(agent.getId());
        agentLevelDao.insert(agentLevel);

        if(agentLevel.getId()>0){
            return ResultJson.ok();
        }else{
            return  ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson updateAgentLevel(long userid, UpdateAgentLevelDto updateAgentLevelDto) {
        //查询代理商信息
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        //查看现有等级
        List<AgentLevel> agentLevelList =agentLevelDao.getAgentLevelListByAgentId(agent.getId());
        AgentLevel updateAgentLevel = null;
        //查询是否有相同的定义数
        for(AgentLevel agentLevel:agentLevelList){

            if(agentLevel.getId() == updateAgentLevelDto.getId()){
                updateAgentLevel = agentLevel;
            }
            if((agentLevel.getNeed_nodes() == updateAgentLevelDto.getNeed_nodes())&&(agentLevel.getId() != updateAgentLevelDto.getId())){
                return ResultJson.failure(ResultCode.BAD_REQUEST,"已设定节点等级");
            }
        }
        updateAgentLevel.setCharges(updateAgentLevelDto.getCharges());
        updateAgentLevel.setNeed_nodes(updateAgentLevelDto.getNeed_nodes());
        updateAgentLevel.setLevel(updateAgentLevelDto.getLevel());
        agentLevelDao.update(updateAgentLevel);
        //批量修改用户的等级
        agentUserDao.updateUserChargesByAgentLevel(updateAgentLevel);
        return  ResultJson.ok();
    }

    @Override
    public ResultJson deleteAgentLevel(long userid, UpdateAgentLevelDto updateAgentLevelDto) {
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        AgentLevel delAgentLevel = agentLevelDao.queryById(updateAgentLevelDto.getId());
        List<AgentUser> userChargesList = agentUserDao.getAgentUserByLevel(delAgentLevel);
        if(userChargesList.size()>0){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"无法删除存在绑定等级用户");
        }
        agentLevelDao.deleteById(updateAgentLevelDto.getId());
        return ResultJson.ok();
    }

    @Override
    public PageInfo<Agent> agentList(NodesFindDto nodesFindDto) {
        PageHelper.startPage(nodesFindDto.getIndex(), nodesFindDto.getSize());
        List<Agent> lists = agentDao.agentList();
        PageInfo<Agent> pageInfo = new PageInfo<Agent>(lists);
        return pageInfo;
    }

    @Override
    public ResultJson userJoinAgent(long userid, UserJoinAgentDto userJoinAgentDto) {
        AgentUser agentUser = agentUserDao.getAgentUserByUseridAndAgentid(userid,userJoinAgentDto.getAgentid());
        if(agentUser!=null){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"已加入代理商");
        }
        agentUser = new AgentUser();
        agentUser.setAgentid(userJoinAgentDto.getAgentid());
        agentUser.setCharges(20);
        agentUser.setLevelid(0);
        agentUser.setState(0);
        agentUser.setPhone(userJoinAgentDto.getPhone());
        agentUser.setNodeNum(userJoinAgentDto.getNode_num());
        agentUser.setUserid((int)userid);
        agentUser.setNote(userJoinAgentDto.getNote());
        agentUserDao.insert(agentUser);
        if(agentUser.getId()>0){
            return  ResultJson.ok();
        }
        return  ResultJson.failure(ResultCode.SERVER_ERROR);
    }

    @Override
    public ResultJson<List<AgentLevel>> agentLevelList(long userid) {
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        List<AgentLevel> agentLevelList =agentLevelDao.getAgentLevelListByAgentId(agent.getId());
        return  ResultJson.ok(agentLevelList);
    }

    @Override
    public PageInfo<AgentUserDto> agentUserList(AgentUserFindDto agentUserFindDto, long userid) {
        if(StringUtils.isEmpty(agentUserFindDto.getSearchtext())){
            agentUserFindDto.setSearchtext(null);
        }
        Agent agent = agentDao.findByOwnerUid(userid);
        agentUserFindDto.setAgentid(agent.getId());
        if (agent == null) {
            return new PageInfo<>();
        }
        PageHelper.startPage(agentUserFindDto.getIndex(), agentUserFindDto.getSize());
        List<AgentUserDto> lists = agentUserDao.agentUserList(agentUserFindDto);
        PageInfo<AgentUserDto> pageInfo = new PageInfo<AgentUserDto>(lists);
        return pageInfo;
    }

    @Override
    public ResultJson agentUserApply(long userid, SinglePramDto singlePramDto) {
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        AgentUserNodesNum agentUserNodesNum = new AgentUserNodesNum();
        agentUserNodesNum.setAgentid(agent.getId());
        agentUserNodesNum.setNode_num(0);
        agentUserNodesNum.setUserid(Integer.valueOf(singlePramDto.getDoid()));
        agentUserNodesNumDao.insert(agentUserNodesNum);
        if(agentUserNodesNum.getId()>0){
            agentUserDao.updateUserStatus(agent.getId(),singlePramDto.getDoid(),1);
        }else{
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        return ResultJson.ok();
    }

    @Override
    public ResultJson frozenUserApply(long userid, SinglePramDto singlePramDto) {
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        //TODO 无法提现
        agentUserDao.updateUserStatus(agent.getId(),singlePramDto.getDoid(),2);
        return ResultJson.ok();
    }

    @Override
    public ResultJson refuseUserApply(long userid, SinglePramDto singlePramDto) {
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        agentUserDao.updateUserStatus(agent.getId(),singlePramDto.getDoid(),4);
        return ResultJson.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson updateAgentUser(AgentUserUpdateDto agentUserUpdateDto, long userid) {
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        AgentUser agentUser = agentUserDao.getAgentUserByUseridAndAgentid(Long.valueOf(agentUserUpdateDto.getUserid()),
                agent.getId());
        agentUser.setNote(agentUserUpdateDto.getNote());
        agentUser.setPhone(agentUserUpdateDto.getPhone());
        agentUser.setNodeNum(agentUserUpdateDto.getNodeNum());
        agentUser.setViptype(agentUserUpdateDto.getViptype());

        //如果是自动变手动
        if(agentUserUpdateDto.getViptype()==1){
            AgentLevel agentLevel = agentLevelDao.queryById(agentUserUpdateDto.getLevelid());
            if(agentLevel==null){
                return ResultJson.failure(ResultCode.BAD_REQUEST);
            }
            agentUser.setCharges(agentLevel.getCharges());
            agentUser.setLevelid(agentUserUpdateDto.getLevelid());
        }
        //手动变自动
        if(agentUserUpdateDto.getViptype()==0){
           //获取用户节点数返回charges
            AgentUserNodesNum agentUserNodesNum = agentUserNodesNumDao.findByUserid(agent.getId(),
                    agentUserUpdateDto.getUserid());
            AgentLevel agentLevel =creatUserAgentLevel(agent.getId(),agentUserNodesNum.getNode_num());
            if(agentLevel!=null){
                agentUser.setCharges(agentLevel.getCharges());
                agentUser.setLevelid(agentLevel.getId());
            }
        }
        agentUserDao.update(agentUser);
        return ResultJson.ok();
    }

    @Override
    public PageInfo<SwarmNodesDto> agentNodesList(AgentNodesFindDto nodesFindDto, long userid) {
        if(StringUtils.isEmpty(nodesFindDto.getSearchtext())){
            nodesFindDto.setSearchtext(null);
        }
        if(StringUtils.isEmpty(nodesFindDto.getArea())){
            nodesFindDto.setArea(null);
        }
        Agent agent = agentDao.findByOwnerUid(userid);
        nodesFindDto.setAgentid(agent.getId());
        if (agent == null) {
            return new PageInfo<>();
        }
        PageHelper.startPage(nodesFindDto.getIndex(), nodesFindDto.getSize());
        List<SwarmNodesDto> lists = agentNodesDao.agentNodesList(nodesFindDto);
        PageInfo<SwarmNodesDto> pageInfo = new PageInfo<SwarmNodesDto>(lists);
        return  pageInfo;
    }

    @Override
    public ResultJson agentUserEnable(long userid, SinglePramDto singlePramDto) {
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        agentUserDao.updateUserStatus(agent.getId(),singlePramDto.getDoid(),1);
        return ResultJson.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson agentUserDelete(long userid, SinglePramDto singlePramDto) {
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        AgentUser agentUser = agentUserDao.getAgentUserByUseridAndAgentid(Integer.valueOf(singlePramDto.getDoid()),agent.getId());
        if(agentUser==null||agentUser.getNodeNum()>0){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"用户存在使用节点请解绑后在操作");
        }
        agentUserDao.deleteById(agentUser.getId());
        agentNodesDao.deleteByAgentIdAndUserId(agent.getId(),singlePramDto.getDoid());
        return ResultJson.ok();
    }


    @Override
    public AgentLevel  creatUserAgentLevel(int agentid,int nodes_num){
        //查找用户节点数量
        List<AgentLevel> agentLevelList = agentLevelDao.getAgentLevelListByAgentId(agentid);
        for(AgentLevel agentLevel:agentLevelList){
            if(agentLevel.getNeed_nodes()>=nodes_num){
                return agentLevel;
            }
        }
        return  agentLevelList.get(agentLevelList.size()-1);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 5   ; i++) {
            if(i==3){
                return ;
            }
            System.out.println(i);
        }
    }
}
