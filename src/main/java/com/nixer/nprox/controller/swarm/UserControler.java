package com.nixer.nprox.controller.swarm;

import com.github.pagehelper.PageInfo;
import com.nixer.nprox.entity.SwarmTokenTotal;
import com.nixer.nprox.entity.common.Role;
import com.nixer.nprox.entity.common.UserDetail;
import com.nixer.nprox.entity.common.dto.*;
import com.nixer.nprox.entity.swarm.Agent;
import com.nixer.nprox.entity.swarm.UserNotebook;
import com.nixer.nprox.entity.swarm.UserOrder;
import com.nixer.nprox.entity.swarm.dto.*;
import com.nixer.nprox.service.AgentService;
import com.nixer.nprox.service.UserNotebookService;
import com.nixer.nprox.service.UserOrderService;
import com.nixer.nprox.service.auth.AuthService;
import com.nixer.nprox.service.swarm.SwarmService;
import com.nixer.nprox.tools.GetIpUtil;
import com.nixer.nprox.tools.ResultCode;
import com.nixer.nprox.tools.ResultJson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * (UserNotebook)表控制层
 *
 * @author makejava
 * @since 2021-06-16 18:25:21
 */
@RestController
@RequestMapping("/user")
public class UserControler {
    /**
     * 服务对象
     */

    @Resource
    private UserOrderService userOrderService;

    @Resource
    private UserNotebookService userNotebookService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private SwarmService swarmService;



    @PreAuthorize("hasAnyRole('USER','AGENT','ADMIN')")
    @PostMapping("/pageJump")
    @ApiOperation(value = "页面跳转")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson pageJump(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        return ResultJson.ok();
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/userNoteBookList")
    @ApiOperation(value = "用户转账地址簿")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<PageInfo<NoteBookDto>> userNoteBookList(HttpServletRequest request,
                                                              @RequestBody AddressBookFindDto addressBookFindDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        PageInfo<NoteBookDto> swarmNodesPageInfo = userNotebookService.userNoteBookList(addressBookFindDto,userid);
        return ResultJson.ok(swarmNodesPageInfo);
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/modifyPassword")
    @ApiOperation(value = "修改密码")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson modifyPassword(HttpServletRequest request, @RequestBody ModifyPasswordDto modifyPasswordDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        modifyPasswordDto.setUserid(userid);
        modifyPasswordDto.setToken(token);
        String ipaddr =  GetIpUtil.getUserIp(request);
        return authService.modifyPassword(modifyPasswordDto,ipaddr);
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/addUserNoteBook")
    @ApiOperation(value = "新增用户转账地址簿")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<UserNotebook> addUserNoteBook(HttpServletRequest request, @RequestBody NoteBookDto noteBookDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        UserNotebook userNotebook = userNotebookService.addUserNoteBook(noteBookDto,userid);
        if(userNotebook!=null){
            return ResultJson.ok(userNotebook);
        }else{
            return ResultJson.failure(ResultCode.SERVER_ERROR);
        }
    }

    //用户账户余额
    @PreAuthorize("hasAnyRole('USER','AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/userAccount")
    @ApiOperation(value = "用户账户")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<UserAccount> userAccount(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        ResultJson<UserAccount> req = userNotebookService.userAccount(userid);
        return  req;
    }
    //用户账单
    @PreAuthorize("hasAnyRole('USER','AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/userOrderList")
    @ApiOperation(value = "用户账单")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<PageInfo<UserOrder>> userOrderList(HttpServletRequest request, @RequestBody NodesFindDto nodesFindDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        PageInfo<UserOrder> swarmNodesPageInfo = userOrderService.userOrderList(nodesFindDto,userid);
        return ResultJson.ok(swarmNodesPageInfo);
    }
    //新增提现订单
    @PreAuthorize("hasAnyRole('USER','AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/addUserOrder")
    @ApiOperation(value = "新增用户提现订单")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson addUserOrder(HttpServletRequest request, @RequestBody UserOrderDto userOrderDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        String ipaddr =  GetIpUtil.getUserIp(request);
        userOrderDto.setDoipaddr(ipaddr);
        ResultJson req = userOrderService.addUserOrder(userid,userOrderDto);
        return  req;
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/agentSign" )
    @ApiOperation(value = "注册代理商")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson agentSign(HttpServletRequest request, @RequestBody LoginDto user){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = new UserDetail(user.getUsername(), user.getPassword(), Role.builder().id(3l).build());
        return ResultJson.ok(authService.register(userDetail, 1));
    }


    //TODO 新增代理商  绑定代理商的时候做 设置代理商提现比例 默认给代理商一个默认等级



    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @PostMapping("/bindUserPhone")
    @ApiOperation(value = "绑定用户手机 ,绑定手机需要绑定手机的验证码 和 原来登录邮箱的验证码")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson bindUserPhone(HttpServletRequest request, @RequestBody BindLoginDtoExt bindLoginDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        String ip = GetIpUtil.getUserIp(request);
        ResultJson req = userOrderService.bindUserPhone(userid,ip,bindLoginDto);
        return  req;
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @PostMapping("/bindUserEmail")
    @ApiOperation(value = "绑定用户邮箱 ,绑定邮箱需要绑定邮箱的验证码和原来登录手机的验证码")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson bindUserEmail(HttpServletRequest request, @RequestBody BindLoginDtoExt bindLoginDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        String ip = GetIpUtil.getUserIp(request);
        ResultJson req = userOrderService.bindUserEmail(userid,ip,bindLoginDto);
        return  req;
    }

    //agentList
    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @PostMapping("/agentList")
    @ApiOperation(value = "代理商列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<PageInfo<Agent>> agentList(HttpServletRequest request, @RequestBody NodesFindDto nodesFindDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
//        UserDetail userDetail = authService.getUserByToken(token);
//        long userid = userDetail.getId();//userid
        nodesFindDto.setState(-1);
        PageInfo<Agent> req = agentService.agentList(nodesFindDto);
        return  ResultJson.ok(req);
    }



    //用户申请加入agent并分要求分配节点
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/userJoinAgent")
    @ApiOperation(value = "用户申请加入agent并分要求分配节点")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson userJoinAgent(HttpServletRequest request, @RequestBody UserJoinAgentDto userJoinAgentDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return  agentService.userJoinAgent(userid,userJoinAgentDto);
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @PostMapping("/beforeUserVerify")
    @ApiOperation(value = "用户操作身份验证前发送验证码 doid  0 为手机 验证 1为邮箱验证")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson beforeUserVerify(HttpServletRequest request, @RequestBody SinglePramDto singlePramDto) throws IOException {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        return  authService.beforeUserVerify(userDetail,singlePramDto);
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @PostMapping("/userVerify")
    @ApiOperation(value = "用户操作身份验证")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson userVerify(HttpServletRequest request, @RequestBody UserVerifyDto userVerifyDto) throws IOException {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        String ipaddress  = GetIpUtil.getUserIp(request);
        return  authService.userVerify(userDetail,userVerifyDto,ipaddress);
    }


    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @PostMapping("/userChangeBind")
    @ApiOperation(value = "用户更换绑定")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson userChangeBind(HttpServletRequest request, @RequestBody ChangeBindDto changeBindDto) throws IOException {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        String ipaddress  = GetIpUtil.getUserIp(request);
        return  authService.userChangeBind(userDetail,changeBindDto,ipaddress);
    }


    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @PostMapping("/userWallet")
    @ApiOperation(value = "用户钱包")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<List<UserWalletDto>> userWallet(HttpServletRequest request) throws IOException {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        return  swarmService.userWallet(userDetail);
    }


    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @PostMapping("/activeWallet")
    @ApiOperation(value = "激活用户钱包")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson activeWallet(HttpServletRequest request,@RequestBody ActiveWalletDto activeWalletDto) throws IOException {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        return  swarmService.activeWallet(userDetail.getId(),activeWalletDto);
    }








}
