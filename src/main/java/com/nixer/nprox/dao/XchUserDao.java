package com.nixer.nprox.dao;

import com.nixer.nprox.entity.XchUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (XchUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-30 16:50:49
 */
@Repository
public interface XchUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    XchUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<XchUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param xchUser 实例对象
     * @return 对象列表
     */
    List<XchUser> queryAll(XchUser xchUser);

    /**
     * 新增数据
     *
     * @param xchUser 实例对象
     * @return 影响行数
     */
    int insert(XchUser xchUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<XchUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<XchUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<XchUser> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<XchUser> entities);

    /**
     * 修改数据
     *
     * @param xchUser 实例对象
     * @return 影响行数
     */
    int update(XchUser xchUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    XchUser findByUserId(long userid);
}

