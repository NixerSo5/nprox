package com.nixer.nprox.dao;

import com.nixer.nprox.entity.SwarmUserDay;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (SwarmUserDay)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-20 14:18:29
 */
@Repository
public interface SwarmUserDayDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SwarmUserDay queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SwarmUserDay> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param swarmUserDay 实例对象
     * @return 对象列表
     */
    List<SwarmUserDay> queryAll(SwarmUserDay swarmUserDay);

    /**
     * 新增数据
     *
     * @param swarmUserDay 实例对象
     * @return 影响行数
     */
    int insert(SwarmUserDay swarmUserDay);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SwarmUserDay> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SwarmUserDay> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SwarmUserDay> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SwarmUserDay> entities);

    /**
     * 修改数据
     *
     * @param swarmUserDay 实例对象
     * @return 影响行数
     */
    int update(SwarmUserDay swarmUserDay);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    SwarmUserDay findByUserid(@Param("userid")long userid, @Param("date")String date);

    void updateNodeNumByUserid(@Param("userid")long userid,@Param("date") String date,@Param("updatenodes") int updatenodes);
}

