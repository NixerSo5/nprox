package com.nixer.nprox.dao;

import com.nixer.nprox.entity.SwarmUserNodes;
import com.nixer.nprox.entity.swarm.dto.NodesUidDto;
import com.nixer.nprox.entity.swarm.dto.UnodesDtoExt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (SwarmUserNodes)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-20 13:50:33
 */
@Repository
public interface SwarmUserNodesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SwarmUserNodes queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SwarmUserNodes> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param swarmUserNodes 实例对象
     * @return 对象列表
     */
    List<SwarmUserNodes> queryAll(SwarmUserNodes swarmUserNodes);

    /**
     * 新增数据
     *
     * @param swarmUserNodes 实例对象
     * @return 影响行数
     */
    int insert(SwarmUserNodes swarmUserNodes);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SwarmUserNodes> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SwarmUserNodes> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SwarmUserNodes> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SwarmUserNodes> entities);

    /**
     * 修改数据
     *
     * @param swarmUserNodes 实例对象
     * @return 影响行数
     */
    int update(SwarmUserNodes swarmUserNodes);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    void deleteUserNodes(@Param("userid")long userid,@Param("list") List<NodesUidDto> list);
}

