package com.nixer.nprox.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nixer.nprox.dao.*;
import com.nixer.nprox.entity.AgentUser;
import com.nixer.nprox.entity.SysLoginType;
import com.nixer.nprox.entity.common.SysConfig;
import com.nixer.nprox.entity.common.UserDetail;
import com.nixer.nprox.entity.common.dto.BindLoginDto;
import com.nixer.nprox.entity.common.dto.BindLoginDtoExt;
import com.nixer.nprox.entity.swarm.*;
import com.nixer.nprox.entity.swarm.dto.NodesFindDto;
import com.nixer.nprox.entity.swarm.dto.UserAccount;
import com.nixer.nprox.entity.swarm.dto.UserOrderDto;
import com.nixer.nprox.entity.swarm.pool.CashOutConfig;
import com.nixer.nprox.service.AgentService;
import com.nixer.nprox.service.UserOrderService;
import com.nixer.nprox.tools.RedisUtil;
import com.nixer.nprox.tools.ResultCode;
import com.nixer.nprox.tools.ResultJson;
import com.nixer.nprox.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * (UserOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-06-16 22:34:56
 */
@Service("userOrderService")
public class UserOrderServiceImpl implements UserOrderService {
    @Resource
    private UserOrderDao userOrderDao;

    @Autowired
    private SwarmDao swarmDao;

    @Autowired
    private UserNotebookDao userNotebookDao;

    @Autowired
    private AgentService  agentService;

    @Autowired
    private UserInfoDao  userInfoDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AgentUserDao agentUserDao;

    @Autowired
    private AgentDao agentDao;

    @Autowired
    private SysLoginTypeDao sysLoginTypeDao;

    @Autowired
    private AuthDao authDao;



    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserOrder queryById(Integer id) {
        return this.userOrderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserOrder> queryAllByLimit(int offset, int limit) {
        return this.userOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userOrder 实例对象
     * @return 实例对象
     */
    @Override
    public UserOrder insert(UserOrder userOrder) {
        this.userOrderDao.insert(userOrder);
        return userOrder;
    }

    /**
     * 修改数据
     *
     * @param userOrder 实例对象
     * @return 实例对象
     */
    @Override
    public UserOrder update(UserOrder userOrder) {
        this.userOrderDao.update(userOrder);
        return this.queryById(userOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userOrderDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo<UserOrder> userOrderList(NodesFindDto nodesFindDto, long userid) {
        PageHelper.startPage(nodesFindDto.getIndex(), nodesFindDto.getSize());
        List<UserOrder> lists = userOrderDao.userOrderList(userid, nodesFindDto.getState());
        PageInfo<UserOrder> pageInfo = new PageInfo<UserOrder>(lists);
        return pageInfo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson addUserOrder(long userid, UserOrderDto userOrderDto) {

        String unlock =  redisUtil.get("USERUNLOCKVERIFY:USERID:"+userid+"_"+userOrderDto.getDoipaddr());
        if(StringUtils.isEmpty(unlock)){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"未解锁无法操作!");
        }
        Agent agent = agentDao.findByOwnerUid(userid);
        if (agent == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        //TODO 大B归属矿池的orgid -1  大B属于管理员?  大b也有抽佣比例
        //TODO 执行提现的操作交易流程
        //是否达到标线
        SysConfig sysConfig =  swarmDao.getSysConfigOutCash();
        CashOutConfig cashOutConfig = JSONObject.parseObject(sysConfig.getConfig_json(),CashOutConfig.class);
        //是否可以体现
        SwarmUserTotalExt userTotal = swarmDao.getSwarmUserTotal(userid);
        UserAccount userAccount = userNotebookDao.userAccount(userid);
        //提现具体实现
        if(userAccount.getCbzz()==null){
            userAccount.setCbzz(new BigDecimal(0));
        }
        if(userAccount.getCashout_bzz() ==null){
            userAccount.setCashout_bzz(new BigDecimal(0));
        }
        BigDecimal climit = cashOutConfig.getMin_bzz().multiply(new BigDecimal(userTotal.getTotal_node_num()));//保底运行
        BigDecimal userCanCashOut = userAccount.getBzz().subtract(userAccount.getCashout_bzz()).subtract(climit);//最低标准

        if(userCanCashOut.compareTo(climit)== -1){
          return  ResultJson.failure("无法提现低于最低节点保留bzz");
        }
        if(cashOutConfig.getCashout_limit().compareTo(userCanCashOut) == 1){
            return  ResultJson.failure("无法提现低于最低提现标准");
        }
        UserOrder userOrder =  new UserOrder();
        userOrder.setUserid((int)userid);
        userOrder.setCurrencyType("bzz");
        userOrder.setServiceType(1);
        userOrder.setQuantity(userOrderDto.getWithdrawNum());
        // 计算手续费
        if(userOrderDto.getWithdrawNum().compareTo(userCanCashOut)==1){
            return  ResultJson.failure("提现金额大于可提现金额");
        }
        //TODO 多个agent
        AgentUser agentUser = agentUserDao.getUserCharges(userid,agent.getId());

        BigDecimal serviceCharges = userOrderDto.getWithdrawNum().multiply(new BigDecimal(agentUser.getCharges()*0.01));
        userOrder.setServiceCharges(serviceCharges);
        userOrder.setStatus(0);
        userOrder.setRemarks(userOrderDto.getRemarks());
        userOrder.setWithdrawAddress(userOrderDto.getWithdrawAddress());
        //生成一个B的收费单据
        int i = userOrderDao.insert(userOrder);

        if(userOrder.getId()>0){
        AgentOrder agentOrder = new AgentOrder();
        agentOrder.setOrderbzz(userOrderDto.getWithdrawNum());
        agentOrder.setOrderid(userOrder.getId());
        agentOrder.setIssend(0);
        agentOrder.setCharges(Long.valueOf(agentUser.getCharges()));
        agentOrder.setGetbzz(serviceCharges);
        agentOrder.setUserid(agent.getOwneruserid());
        i = agentService.insertAgentOrder(agentOrder);
           if(agentOrder.getId()>0){
               return ResultJson.ok("提交成功!");
           }else{
               return ResultJson.failure(ResultCode.SERVER_ERROR);
           }
        }else{
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }

    @Override
    public ResultJson bindUserPhone(long userid,String ipaddress, BindLoginDtoExt bindLoginDto) {
        String unlockkey = redisUtil.get("USERUNLOCKVERIFY:USERID:"+userid+"_"+ipaddress);
        if(StringUtils.isEmpty(unlockkey)){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"账号未解锁无法操作");
        }
        UserInfo userInfo = userInfoDao.findByUserid(userid);
        UserInfo fuserInfo = userInfoDao.findByPhone(bindLoginDto.getBind());
        SysLoginType alluser = sysLoginTypeDao.findByLoginName(bindLoginDto.getBind());
        if(fuserInfo!=null||alluser!=null){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"该手机号已经被其他账号绑定");
        }
        SysLoginType sysLoginType = sysLoginTypeDao.findByUserIdAndLoginType(userid,1);
        if(sysLoginType!=null||!StringUtils.isEmpty(userInfo.getPhone())){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"该账号已经绑定过手机");
        }
        if(userInfo!=null&&!StringUtils.isEmpty(userInfo.getEmail())){
                String value2 = redisUtil.get("SENDSMS:" + bindLoginDto.getBind());
                if (value2 != null && value2.equals(bindLoginDto.getBindcode())) {
                    UserDetail userDetail = authDao.findById(userid);
                    sysLoginType = new SysLoginType();
                    sysLoginType.setLogin_type(1);
                    sysLoginType.setSys_username(userDetail.getUsername());
                    sysLoginType.setUserid((int) userid);
                    sysLoginType.setLogin_name(bindLoginDto.getBind());
                    sysLoginTypeDao.insert(sysLoginType);
                    if(sysLoginType.getId()>0){
                        userInfo.setPhone(bindLoginDto.getBind());
                        userInfoDao.update(userInfo);
                    }
                    redisUtil.remove("USERUNLOCKVERIFY:USERID:"+userid+"_"+ipaddress);
                    return ResultJson.ok();
                }
        }
        return ResultJson.failure(ResultCode.BAD_REQUEST,"手机未绑定或者用户不存在");
    }

    @Override
    public ResultJson bindUserEmail(long userid,String ipaddress, BindLoginDtoExt bindLoginDto) {
        String unlockkey = redisUtil.get("USERUNLOCKVERIFY:USERID:"+userid+"_"+ipaddress);
        if(StringUtils.isEmpty(unlockkey)){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"账号未解锁无法操作");
        }
        UserInfo userInfo = userInfoDao.findByUserid(userid);
        UserInfo fuserInfo = userInfoDao.findByEmail(bindLoginDto.getBind());
        SysLoginType alluser = sysLoginTypeDao.findByLoginName(bindLoginDto.getBind());
        if(fuserInfo!=null||alluser!=null){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"该邮箱已经被其他账号绑定");
        }
        SysLoginType sysLoginType = sysLoginTypeDao.findByUserIdAndLoginType(userid,2);
        if(sysLoginType!=null||!StringUtils.isEmpty(userInfo.getEmail())){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"该账号已经绑定过邮箱");
        }
        if(userInfo!=null&&!StringUtils.isEmpty(userInfo.getPhone())){
                String value2 = redisUtil.get("SENDEMAIL:" + bindLoginDto.getBind());
                if (value2 != null && value2.equals(bindLoginDto.getBindcode())) {
                    UserDetail userDetail = authDao.findById(userid);
                    sysLoginType = new SysLoginType();
                    sysLoginType.setLogin_type(2);
                    sysLoginType.setSys_username(userDetail.getUsername());
                    sysLoginType.setUserid((int) userid);
                    sysLoginType.setLogin_name(bindLoginDto.getBind());
                    sysLoginTypeDao.insert(sysLoginType);
                    if(sysLoginType.getId()>0){
                        userInfo.setEmail(bindLoginDto.getBind());
                        userInfoDao.update(userInfo);
                    }
                    redisUtil.remove("USERUNLOCKVERIFY:USERID:"+userid+"_"+ipaddress);
                    return ResultJson.ok();
                }
        }
        return ResultJson.failure(ResultCode.BAD_REQUEST,"邮箱未绑定或者用户不存在");
    }
    //TODO 提现成功
}
