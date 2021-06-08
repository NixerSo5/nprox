package com.nixer.nprox.service;

import com.nixer.nprox.entity.NUser;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * (NUser)表服务接口
 *
 * @author makejava
 * @since 2021-06-07 16:45:30
 */
public interface NUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    NUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<NUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param nUser 实例对象
     * @return 实例对象
     */
    NUser insert(NUser nUser);

    /**
     * 修改数据
     *
     * @param nUser 实例对象
     * @return 实例对象
     */
    NUser update(NUser nUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    NUser getUserByName(String username);

    int updatePwd(String oldPwd, String newPwd);

    Authentication authenticate(String username, String password);
}
