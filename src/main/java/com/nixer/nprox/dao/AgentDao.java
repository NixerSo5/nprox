package com.nixer.nprox.dao;

import com.nixer.nprox.entity.swarm.Agent;
import com.nixer.nprox.entity.swarm.AgentOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Agent)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-17 22:55:39
 */
@Repository
public interface AgentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Agent queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Agent> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param agent 实例对象
     * @return 对象列表
     */
    List<Agent> queryAll(Agent agent);

    /**
     * 新增数据
     *
     * @param agent 实例对象
     * @return 影响行数
     */
    int insert(Agent agent);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Agent> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Agent> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Agent> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Agent> entities);

    /**
     * 修改数据
     *
     * @param agent 实例对象
     * @return 影响行数
     */
    int update(Agent agent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int insertAgentOrder(AgentOrder agentOrder);

    Agent findByOwnerUid(long userid);

    List<Agent> agentList();
}

