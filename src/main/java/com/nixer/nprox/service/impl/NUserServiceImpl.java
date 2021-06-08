package com.nixer.nprox.service.impl;

import com.nixer.nprox.entity.NUser;
import com.nixer.nprox.dao.NUserDao;
import com.nixer.nprox.exception.CustomException;
import com.nixer.nprox.service.NUserService;
import com.nixer.nprox.tools.ResultCode;
import com.nixer.nprox.tools.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (NUser)表服务实现类
 *
 * @author makejava
 * @since 2021-06-07 16:45:31
 */
@Service("nUserService")
public class NUserServiceImpl implements NUserService {
    @Resource
    private NUserDao nUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public NUser queryById(Integer id) {
        return this.nUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<NUser> queryAllByLimit(int offset, int limit) {
        return this.nUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param nUser 实例对象
     * @return 实例对象
     */
    @Override
    public NUser insert(NUser nUser) {
        nUser.setPassword(passwordEncoder.encode(nUser.getPassword()));
        this.nUserDao.insert(nUser);
        return nUser;
    }

    /**
     * 修改数据
     *
     * @param nUser 实例对象
     * @return 实例对象
     */
    @Override
    public NUser update(NUser nUser) {
        this.nUserDao.update(nUser);
        return this.queryById(nUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.nUserDao.deleteById(id) > 0;
    }

    @Override
    public NUser getUserByName(String username) {
        return this.nUserDao.getUserByName(username);
    }

    @Override
    public int updatePwd(String oldPwd, String newPwd) {
        // 获取当前登录用户信息(注意：没有密码的)
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        // 通过用户名获取到用户信息（获取密码）
        NUser userInfo = nUserDao.getUserByName(username);

        // 判断输入的旧密码是正确
        if (passwordEncoder.matches(oldPwd, userInfo.getPassword())) {
            // 不要忘记加密新密码
            return nUserDao.updatePwd(userInfo.getId(), passwordEncoder.encode(newPwd));
        }
        return 0;
    }

    @Override
    public Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }
}
