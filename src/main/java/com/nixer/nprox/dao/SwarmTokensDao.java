package com.nixer.nprox.dao;

import com.nixer.nprox.entity.SwarmTokens;
import com.nixer.nprox.entity.swarm.dto.WalletInfoDto;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (SwarmTokens)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-06 11:55:54
 */
@Repository
public interface SwarmTokensDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SwarmTokens queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SwarmTokens> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param swarmTokens 实例对象
     * @return 对象列表
     */
    List<SwarmTokens> queryAll(SwarmTokens swarmTokens);

    /**
     * 新增数据
     *
     * @param swarmTokens 实例对象
     * @return 影响行数
     */
    int insert(SwarmTokens swarmTokens);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SwarmTokens> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SwarmTokens> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SwarmTokens> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SwarmTokens> entities);

    /**
     * 修改数据
     *
     * @param swarmTokens 实例对象
     * @return 影响行数
     */
    int update(SwarmTokens swarmTokens);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<SwarmTokens> tokensList(long userid, Integer state);

    SwarmTokens tokenDetail(int id);
}

