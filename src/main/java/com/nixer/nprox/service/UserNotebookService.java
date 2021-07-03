package com.nixer.nprox.service;

import com.github.pagehelper.PageInfo;
import com.nixer.nprox.entity.swarm.UserNotebook;
import com.nixer.nprox.entity.swarm.UserOrder;
import com.nixer.nprox.entity.swarm.dto.NodesFindDto;
import com.nixer.nprox.entity.swarm.dto.NoteBookDto;
import com.nixer.nprox.entity.swarm.dto.UserAccount;
import com.nixer.nprox.entity.swarm.dto.UserOrderDto;
import com.nixer.nprox.tools.ResultJson;

import java.util.List;

/**
 * (UserNotebook)表服务接口
 *
 * @author makejava
 * @since 2021-06-16 18:25:20
 */
public interface UserNotebookService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserNotebook queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserNotebook> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userNotebook 实例对象
     * @return 实例对象
     */
    UserNotebook insert(UserNotebook userNotebook);

    /**
     * 修改数据
     *
     * @param userNotebook 实例对象
     * @return 实例对象
     */
    UserNotebook update(UserNotebook userNotebook);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);



    PageInfo<NoteBookDto> userNoteBookList(NodesFindDto nodesFindDto, long userid);

    UserNotebook addUserNoteBook(NoteBookDto noteBookDto, long userid);

    ResultJson<UserAccount> userAccount(long userid);

}
