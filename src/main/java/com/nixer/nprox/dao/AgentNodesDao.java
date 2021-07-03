package com.nixer.nprox.dao;

import com.nixer.nprox.entity.AgentNodes;
import com.nixer.nprox.entity.swarm.dto.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (AgentNodes)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-18 12:00:38
 */
@Repository
public interface AgentNodesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AgentNodes queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AgentNodes> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param agentNodes 实例对象
     * @return 对象列表
     */
    List<AgentNodes> queryAll(AgentNodes agentNodes);

    /**
     * 新增数据
     *
     * @param agentNodes 实例对象
     * @return 影响行数
     */
    int insert(AgentNodes agentNodes);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AgentNodes> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AgentNodes> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AgentNodes> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<AgentNodes> entities);

    /**
     * 修改数据
     *
     * @param agentNodes 实例对象
     * @return 影响行数
     */
    int update(AgentNodes agentNodes);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<NodeNumDto> agentPoolState(long userid);

    List<SwarmNodesDto> agentNodesList(AgentNodesFindDto nodesFindDto);

    int findNodesBindStatus(UnodesDtoExt unodesDtoExt);

    //TODO  修改sql in
    void updateAgentNodes(@Param("id") Integer id, @Param("nodesUidDtoList") List<NodesUidDto> nodesUidDtoList,
                          @Param("isuse")  int isuse);

    List<AgentNodes> findUnUseNodesLimit(Integer nodesnum);

    void deleteByAgentIdAndUserId(@Param("agentid")Integer agentid, @Param("userid")String userid);
}

