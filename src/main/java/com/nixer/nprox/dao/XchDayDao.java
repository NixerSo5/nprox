package com.nixer.nprox.dao;

import com.nixer.nprox.entity.XchDay;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (XchDay)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-30 15:23:33
 */
@Repository
public interface XchDayDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    XchDay queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<XchDay> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param xchDay 实例对象
     * @return 对象列表
     */
    List<XchDay> queryAll(XchDay xchDay);

    /**
     * 新增数据
     *
     * @param xchDay 实例对象
     * @return 影响行数
     */
    int insert(XchDay xchDay);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<XchDay> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<XchDay> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<XchDay> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<XchDay> entities);

    /**
     * 修改数据
     *
     * @param xchDay 实例对象
     * @return 影响行数
     */
    int update(XchDay xchDay);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

