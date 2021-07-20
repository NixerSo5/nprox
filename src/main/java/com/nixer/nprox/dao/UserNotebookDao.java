package com.nixer.nprox.dao;

import com.nixer.nprox.entity.swarm.UserNotebook;
import com.nixer.nprox.entity.swarm.dto.NoteBookDto;
import com.nixer.nprox.entity.swarm.dto.UserAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (UserNotebook)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-16 18:25:19
 */
@Repository
public interface UserNotebookDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserNotebook queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserNotebook> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userNotebook 实例对象
     * @return 对象列表
     */
    List<UserNotebook> queryAll(UserNotebook userNotebook);

    /**
     * 新增数据
     *
     * @param userNotebook 实例对象
     * @return 影响行数
     */
    int insert(UserNotebook userNotebook);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserNotebook> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserNotebook> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserNotebook> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<UserNotebook> entities);

    /**
     * 修改数据
     *
     * @param userNotebook 实例对象
     * @return 影响行数
     */
    int update(UserNotebook userNotebook);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<NoteBookDto> userNoteBookList(@Param("userid")long userid,@Param("tokenid") Integer tokenid);

    UserAccount userAccount(long userid);
}

