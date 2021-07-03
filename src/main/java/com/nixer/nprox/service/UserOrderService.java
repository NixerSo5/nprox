package com.nixer.nprox.service;

import com.github.pagehelper.PageInfo;
import com.nixer.nprox.entity.common.dto.BindLoginDto;
import com.nixer.nprox.entity.common.dto.BindLoginDtoExt;
import com.nixer.nprox.entity.swarm.UserOrder;
import com.nixer.nprox.entity.swarm.dto.NodesFindDto;
import com.nixer.nprox.entity.swarm.dto.UserOrderDto;
import com.nixer.nprox.tools.ResultJson;

import java.util.List;

/**
 * (UserOrder)表服务接口
 *
 * @author makejava
 * @since 2021-06-16 22:34:56
 */
public interface UserOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserOrder queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userOrder 实例对象
     * @return 实例对象
     */
    UserOrder insert(UserOrder userOrder);

    /**
     * 修改数据
     *
     * @param userOrder 实例对象
     * @return 实例对象
     */
    UserOrder update(UserOrder userOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    PageInfo<UserOrder> userOrderList(NodesFindDto nodesFindDto, long userid);

    ResultJson addUserOrder(long userid, UserOrderDto userOrderDto);

    ResultJson bindUserPhone(long userid,String ipaddress, BindLoginDtoExt bindLoginDto);

    ResultJson bindUserEmail(long userid,String ipaddress, BindLoginDtoExt bindLoginDto);
}
