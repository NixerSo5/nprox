package com.nixer.nprox.controller.swarm;


import com.github.pagehelper.PageInfo;
import com.nixer.nprox.dao.AgentUserDao;
import com.nixer.nprox.entity.AgentLevel;
import com.nixer.nprox.entity.AgentUser;
import com.nixer.nprox.entity.common.UserDetail;
import com.nixer.nprox.entity.common.dto.SinglePramDto;
import com.nixer.nprox.entity.swarm.SwarmNodes;
import com.nixer.nprox.entity.swarm.dto.*;
import com.nixer.nprox.service.AgentNodeService;
import com.nixer.nprox.service.AgentService;
import com.nixer.nprox.service.auth.AuthService;
import com.nixer.nprox.tools.ResultCode;
import com.nixer.nprox.tools.ResultJson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/agent")
public class AgentController {


    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AgentService agentService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AgentNodeService agentNodeService;


    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/agentPoolState")
    @ApiOperation(value = "代理商节点状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<AgentPoolState> agentPoolState(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        AgentPoolState agentPoolState = agentService.agentPoolState(userid);
        return ResultJson.ok(agentPoolState);
    }


    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/addAgentLevel")
    @ApiOperation(value = "add代理商等级")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson addAgentLevel(HttpServletRequest request, @RequestBody AgentLevelDto agentLevelDto) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentService.addAgentLevel(userid,agentLevelDto);
    }

    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/updateAgentLevel")
    @ApiOperation(value = "update代理商等级")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson updateAgentLevel(HttpServletRequest request, @RequestBody UpdateAgentLevelDto updateAgentLevelDto) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentService.updateAgentLevel(userid,updateAgentLevelDto);
    }

    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/deleteAgentLevel")
    @ApiOperation(value = "del代理商等级")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson deleteAgentLevel(HttpServletRequest request, @RequestBody UpdateAgentLevelDto updateAgentLevelDto) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentService.deleteAgentLevel(userid,updateAgentLevelDto);
    }


    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/agentLevelList")
    @ApiOperation(value = "代理商等级列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<List<AgentLevel>> agentLevelList(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentService.agentLevelList(userid);
    }



    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/agentUserList")
    @ApiOperation(value = "代理商用户列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<PageInfo<AgentUserDto>> agentUserList(HttpServletRequest request,
                                                            @RequestBody AgentUserFindDto agentUserFindDto) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        PageInfo pageInfo = agentService.agentUserList(agentUserFindDto,userid);
        return ResultJson.ok(pageInfo);
    }


    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/updateAgentUser")
    @ApiOperation(value = "更新代理商用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson updateAgentUser(HttpServletRequest request,
                                                            @RequestBody AgentUserUpdateDto agentUserUpdateDto) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentService.updateAgentUser(agentUserUpdateDto,userid);
    }


    //agent节点列表
    // 服务费账单
    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/agentUserApply")
    @ApiOperation(value = "代理商用户审核通过 doid为用户id")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson agentUserApply(HttpServletRequest request,
                                                            @RequestBody SinglePramDto singlePramDto) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentService.agentUserApply(userid,singlePramDto);
    }


    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/agentUserRefuse")
    @ApiOperation(value = "代理商用户拒绝通过 doid为用户id")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson agentUserRefuse(HttpServletRequest request,
                                     @RequestBody SinglePramDto singlePramDto) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentService.refuseUserApply(userid,singlePramDto);
    }

    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/agentUserFrozen")
    @ApiOperation(value = "代理商用户冻结 doid为用户id")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson agentUserFrozen(HttpServletRequest request,
                                     @RequestBody SinglePramDto singlePramDto) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentService.frozenUserApply(userid,singlePramDto);
    }


    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/agentUserEnable")
    @ApiOperation(value = "代理商用户启用 doid为用户id")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson agentUserEnable(HttpServletRequest request,
                                      @RequestBody SinglePramDto singlePramDto) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentService.agentUserEnable(userid,singlePramDto);
    }



    @PreAuthorize("hasAnyRole('AGENT')") //
    @PostMapping("/agentUserDelete")
    @ApiOperation(value = "删除代理商用户 doid为用户id")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson agentUserDelete(HttpServletRequest request,
                                      @RequestBody SinglePramDto singlePramDto) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentService.agentUserDelete(userid,singlePramDto);
    }


    //节点列表
    @PreAuthorize("hasAnyRole('AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/agentNodesList")
    @ApiOperation(value = "代理商节点列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<PageInfo<SwarmNodesDto>> agentNodesList(HttpServletRequest request,
                                                              @RequestBody AgentNodesFindDto nodesFindDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        PageInfo<SwarmNodesDto> swarmNodesPageInfo = agentService.agentNodesList(nodesFindDto,userid);
        return ResultJson.ok(swarmNodesPageInfo);
    }


    //分配节点betch
    @PreAuthorize("hasAnyRole('AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/distributionNodes")
    @ApiOperation(value = "分配节点")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<AgentPoolState> distributionNodes(HttpServletRequest request,
                                                              @RequestBody UnodesDtoExt unodesDtoExt) throws ParseException {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentNodeService.distributionNodes(unodesDtoExt,userid);
    }

    @PreAuthorize("hasAnyRole('AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/deallocationNodes")
    @ApiOperation(value = "取消分配用户节点")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<AgentPoolState> deallocationNodes(HttpServletRequest request,
                                                        @RequestBody UnodesDtoExt unodesDtoExt) throws ParseException {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentNodeService.deallocationNodes(unodesDtoExt,userid);
    }


    @PreAuthorize("hasAnyRole('AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/batchDistributionNodes")
    @ApiOperation(value = "批量分配用户节点")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<AgentPoolState> batchDistributionNodes(HttpServletRequest request,
                                                        @RequestBody UnodeBetchExt unodeBetchExt) throws ParseException {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        return agentNodeService.batchDistributionNodes(userid,unodeBetchExt);
    }

}
