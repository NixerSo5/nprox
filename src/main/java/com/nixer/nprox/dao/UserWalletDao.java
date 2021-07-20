package com.nixer.nprox.dao;

import com.nixer.nprox.entity.UserWallet;
import com.nixer.nprox.entity.swarm.dto.UserWalletDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (UserWallet)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-06 12:05:47
 */
@Repository
public interface UserWalletDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserWallet queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserWallet> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userWallet 实例对象
     * @return 对象列表
     */
    List<UserWallet> queryAll(UserWallet userWallet);

    /**
     * 新增数据
     *
     * @param userWallet 实例对象
     * @return 影响行数
     */
    int insert(UserWallet userWallet);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserWallet> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserWallet> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserWallet> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<UserWallet> entities);

    /**
     * 修改数据
     *
     * @param userWallet 实例对象
     * @return 影响行数
     */
    int update(UserWallet userWallet);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<UserWalletDto> getWalletListByUserId(long userid);

    UserWallet getWalletByUserIdAndTokenId(@Param("userid")long userid, @Param("tokenid")String tokenid);

    UserWallet findByUserAndTokenId(@Param("userid")long userid, @Param("tokenid")Integer tokenid);

    int updateWalletBlance(@Param("walletid")long walletid, @Param("sublog")long sublog);
}

