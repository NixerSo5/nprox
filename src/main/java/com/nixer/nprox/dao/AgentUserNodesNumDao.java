package com.nixer.nprox.dao;

import com.nixer.nprox.entity.AgentUserNodesNum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (AgentUserNodesNum)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-19 21:47:30
 */
@Repository
public interface AgentUserNodesNumDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AgentUserNodesNum queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AgentUserNodesNum> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param agentUserNodesNum 实例对象
     * @return 对象列表
     */
    List<AgentUserNodesNum> queryAll(AgentUserNodesNum agentUserNodesNum);

    /**
     * 新增数据
     *
     * @param agentUserNodesNum 实例对象
     * @return 影响行数
     */
    int insert(AgentUserNodesNum agentUserNodesNum);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AgentUserNodesNum> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AgentUserNodesNum> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AgentUserNodesNum> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<AgentUserNodesNum> entities);

    /**
     * 修改数据
     *
     * @param agentUserNodesNum 实例对象
     * @return 影响行数
     */
    int update(AgentUserNodesNum agentUserNodesNum);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    AgentUserNodesNum findByUserid(@Param("agentid")Integer agentid, @Param("userid")String userid);

    void updateAgentUserNodesNum(@Param("userid")Integer userid, @Param("agentid")Integer agentid,
                                 @Param("updatenodes") int updatenodes);
}

