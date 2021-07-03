package com.nixer.nprox.service;

import com.github.pagehelper.PageInfo;
import com.nixer.nprox.entity.AgentLevel;
import com.nixer.nprox.entity.common.dto.SinglePramDto;
import com.nixer.nprox.entity.swarm.Agent;
import com.nixer.nprox.entity.swarm.AgentOrder;
import com.nixer.nprox.entity.swarm.dto.*;
import com.nixer.nprox.tools.ResultJson;

import java.util.List;

/**
 * (Agent)表服务接口
 *
 * @author makejava
 * @since 2021-06-17 22:55:39
 */
public interface AgentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Agent queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Agent> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param agent 实例对象
     * @return 实例对象
     */
    Agent insert(Agent agent);

    /**
     * 修改数据
     *
     * @param agent 实例对象
     * @return 实例对象
     */
    Agent update(Agent agent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    int insertAgentOrder(AgentOrder agentOrder);

    AgentPoolState agentPoolState(long userid);

    ResultJson addAgentLevel(long userid, AgentLevelDto agentLevelDto);

    ResultJson updateAgentLevel(long userid, UpdateAgentLevelDto updateAgentLevelDto);

    ResultJson deleteAgentLevel(long userid, UpdateAgentLevelDto updateAgentLevelDto);

    PageInfo<Agent> agentList(NodesFindDto nodesFindDto);

    ResultJson userJoinAgent(long userid, UserJoinAgentDto userJoinAgentDto);

    ResultJson<List<AgentLevel>> agentLevelList(long userid);

    PageInfo<AgentUserDto> agentUserList(AgentUserFindDto userid, long l);

    ResultJson agentUserApply(long userid, SinglePramDto singlePramDto);

    ResultJson frozenUserApply(long userid, SinglePramDto singlePramDto);

    ResultJson refuseUserApply(long userid, SinglePramDto singlePramDto);

    ResultJson updateAgentUser(AgentUserUpdateDto agentUserUpdateDto, long userid);

    AgentLevel creatUserAgentLevel(int agentid,int nodes_num);

    PageInfo<SwarmNodesDto> agentNodesList(AgentNodesFindDto nodesFindDto, long userid);

    ResultJson agentUserEnable(long userid, SinglePramDto singlePramDto);

    ResultJson agentUserDelete(long userid, SinglePramDto singlePramDto);
}
