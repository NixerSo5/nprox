package com.nixer.nprox.dao;

import com.nixer.nprox.entity.SwarmUserTotal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (SwarmUserTotal)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-20 14:18:38
 */
@Repository
public interface SwarmUserTotalDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SwarmUserTotal queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SwarmUserTotal> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param swarmUserTotal 实例对象
     * @return 对象列表
     */
    List<SwarmUserTotal> queryAll(SwarmUserTotal swarmUserTotal);

    /**
     * 新增数据
     *
     * @param swarmUserTotal 实例对象
     * @return 影响行数
     */
    int insert(SwarmUserTotal swarmUserTotal);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SwarmUserTotal> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SwarmUserTotal> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SwarmUserTotal> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SwarmUserTotal> entities);

    /**
     * 修改数据
     *
     * @param swarmUserTotal 实例对象
     * @return 影响行数
     */
    int update(SwarmUserTotal swarmUserTotal);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    SwarmUserTotal findByUserId(long userid);

    void updateNodeNumByUserid(@Param("userid")long userid,@Param("updatenodes") int updatenodes);
}

