package com.nixer.nprox.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nixer.nprox.dao.UserNotebookDao;
import com.nixer.nprox.entity.swarm.UserNotebook;
import com.nixer.nprox.entity.swarm.UserOrder;
import com.nixer.nprox.entity.swarm.dto.*;
import com.nixer.nprox.service.UserNotebookService;
import com.nixer.nprox.tools.ResultCode;
import com.nixer.nprox.tools.ResultJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * (UserNotebook)表服务实现类
 *
 * @author makejava
 * @since 2021-06-16 18:25:20
 */
@Service("userNotebookService")
public class UserNotebookServiceImpl implements UserNotebookService {
    @Resource
    private UserNotebookDao userNotebookDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserNotebook queryById(Integer id) {
        return this.userNotebookDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserNotebook> queryAllByLimit(int offset, int limit) {
        return this.userNotebookDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userNotebook 实例对象
     * @return 实例对象
     */
    @Override
    public UserNotebook insert(UserNotebook userNotebook) {
        this.userNotebookDao.insert(userNotebook);
        return userNotebook;
    }

    /**
     * 修改数据
     *
     * @param userNotebook 实例对象
     * @return 实例对象
     */
    @Override
    public UserNotebook update(UserNotebook userNotebook) {
        this.userNotebookDao.update(userNotebook);
        return this.queryById(userNotebook.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userNotebookDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo<NoteBookDto> userNoteBookList(NodesFindDto nodesFindDto, long userid) {
        PageHelper.startPage(nodesFindDto.getIndex(), nodesFindDto.getSize());
        List<NoteBookDto> lists = userNotebookDao.userNoteBookList(userid,nodesFindDto.getState());
        PageInfo<NoteBookDto> pageInfo = new PageInfo<NoteBookDto>(lists);
        return pageInfo;
    }

    @Override
    public UserNotebook addUserNoteBook(NoteBookDto noteBookDto, long userid) {
        UserNotebook userNotebook = JSONObject.parseObject(JSONObject.toJSONString(noteBookDto),UserNotebook.class);
        userNotebook.setUserid((int)userid);
        this.userNotebookDao.insert(userNotebook);
        if(userNotebook.getId()>0){
            return userNotebook;
        }else{
            return null;
        }
    }

    public static final  BigDecimal GCD = new BigDecimal(0.0000000000000001);

    @Override
    public ResultJson<UserAccount> userAccount(long userid) {
        UserAccount userAccount = this.userNotebookDao.userAccount(userid);
        if(userAccount.getCbzz()==null){
            userAccount.setCbzz(new BigDecimal(0));
        }
        if(userAccount.getCashout_bzz() ==null){
            userAccount.setCashout_bzz(new BigDecimal(0));
        }
        BigDecimal userCanCashOut = userAccount.getBzz().subtract(userAccount.getCashout_bzz()).multiply(GCD);
        userAccount.setCbzz(userCanCashOut);
        userAccount.setBzz(userAccount.getBzz().multiply(GCD));
        userAccount.setCash_dai(userAccount.getCash_dai().multiply(GCD));
        userAccount.setCashout_bzz(userAccount.getCashout_bzz().multiply(GCD));
        if(userAccount!=null){
            return ResultJson.ok(userAccount);
        }else{
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }



}
