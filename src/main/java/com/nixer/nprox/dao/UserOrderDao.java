package com.nixer.nprox.dao;

import com.nixer.nprox.entity.swarm.AgentOrder;
import com.nixer.nprox.entity.swarm.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserOrder)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-16 22:34:55
 */
public interface UserOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserOrder queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserOrder> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userOrder 实例对象
     * @return 对象列表
     */
    List<UserOrder> queryAll(UserOrder userOrder);

    /**
     * 新增数据
     *
     * @param userOrder 实例对象
     * @return 影响行数
     */
    int insert(UserOrder userOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserOrder> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<UserOrder> entities);

    /**
     * 修改数据
     *
     * @param userOrder 实例对象
     * @return 影响行数
     */
    int update(UserOrder userOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<UserOrder> userOrderList(@Param("userid") long userid,@Param("state")  Integer state);

}

