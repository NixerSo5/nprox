package com.nixer.nprox.dao;

import com.nixer.nprox.entity.AgentLevel;
import com.nixer.nprox.entity.AgentUser;
import com.nixer.nprox.entity.swarm.dto.AgentUserDto;
import com.nixer.nprox.entity.swarm.dto.AgentUserFindDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (AgentUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-19 17:29:58
 */
@Repository
public interface AgentUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AgentUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AgentUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param agentUser 实例对象
     * @return 对象列表
     */
    List<AgentUser> queryAll(AgentUser agentUser);

    /**
     * 新增数据
     *
     * @param agentUser 实例对象
     * @return 影响行数
     */
    int insert(AgentUser agentUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AgentUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AgentUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AgentUser> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<AgentUser> entities);

    /**
     * 修改数据
     *
     * @param agentUser 实例对象
     * @return 影响行数
     */
    int update(AgentUser agentUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    AgentUser getUserCharges(@Param("userid")long userid,@Param("agentid") int agentid);

    List<AgentUser> getAgentUserByLevel(AgentLevel delAgentLevel);

    void updateUserChargesByAgentLevel(AgentLevel updateAgentLevel);

    AgentUser getAgentUserByUseridAndAgentid(long userid, Integer agentid);

    List<AgentUserDto> agentUserList(AgentUserFindDto agentUserFindDto);

    void updateUserStatus(@Param("agentid")Integer id, @Param("userid")String userid, @Param("status")int status);

    void updateUserLevelAndCharge(@Param("agentLevel")AgentLevel agentLevel, @Param("userid")Integer userid);
}

