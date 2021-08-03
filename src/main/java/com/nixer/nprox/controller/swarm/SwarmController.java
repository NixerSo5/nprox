package com.nixer.nprox.controller.swarm;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.nixer.nprox.entity.SwarmTokenTotal;
import com.nixer.nprox.entity.SwarmTokens;
import com.nixer.nprox.entity.common.UserDetail;
import com.nixer.nprox.entity.common.dto.PageFindDto;
import com.nixer.nprox.entity.swarm.SwarmDay;
import com.nixer.nprox.entity.swarm.SwarmNodes;
import com.nixer.nprox.entity.swarm.SwarmUserTotal;
import com.nixer.nprox.entity.swarm.dto.*;
import com.nixer.nprox.service.auth.AuthService;
import com.nixer.nprox.service.swarm.SwarmService;
import com.nixer.nprox.tools.JwtUtils;
import com.nixer.nprox.tools.ResultCode;
import com.nixer.nprox.tools.ResultJson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/swarm")
public class SwarmController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private SwarmService swarmService;


    @Autowired
    private AuthService authService;




    //节点列表
    @PreAuthorize("hasAnyRole('USER')") // 只能user角色才能访问该方法
    @PostMapping("/userNodesList")
    @ApiOperation(value = "用户节点状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<PageInfo<SwarmNodes>> userNodesList(HttpServletRequest request, @RequestBody NodesFindDto nodesFindDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        PageInfo<SwarmNodes> swarmNodesPageInfo = swarmService.useNodesList(nodesFindDto,userid);
        return ResultJson.ok(swarmNodesPageInfo);
    }




    @PreAuthorize("hasAnyRole('USER','AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/tokensList")
    @ApiOperation(value = "token种类列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<PageInfo<SwarmTokens>> tokensList(HttpServletRequest request, @RequestBody PageFindDto pageFindDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        PageInfo<SwarmTokens> swarmNodesPageInfo = swarmService.tokensList(pageFindDto,userid);
        return ResultJson.ok(swarmNodesPageInfo);
    }



    @PreAuthorize("hasAnyRole('USER','AGENT')") // 只能user角色才能访问该方法
    @PostMapping("/tokensInfo")
    @ApiOperation(value = "token状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<SwarmTokenTotal> tokensInfo(HttpServletRequest request, @RequestBody WalletInfoDto walletInfoDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        SwarmTokenTotal swarmNodesPageInfo = swarmService.tokensInfo(walletInfoDto);
        return ResultJson.ok(swarmNodesPageInfo);
    }


//    //新增节点
//    @PreAuthorize("hasAnyRole('USER')") // 只能user角色才能访问该方法
//    @PostMapping("/userNodesAdd")
//    @ApiOperation(value = "用户节点新增")
//    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
//    public ResultJson userNodesAdd(HttpServletRequest request, @RequestBody UserNodeUpdateDto userNodeUpdateDto){
//        String token = request.getHeader(tokenHeader);
//        if (token == null) {
//            return ResultJson.failure(ResultCode.UNAUTHORIZED);
//        }
//        UserDetail userDetail = authService.getUserByToken(token);
//        long userid = userDetail.getId();//userid
//        ResultJson resultJson = swarmService.useNodesAdd(userNodeUpdateDto,userid);
//        return resultJson;
//    }
//    //删除节点
//    @PreAuthorize("hasAnyRole('USER')") // 只能user角色才能访问该方法
//    @PostMapping("/userNodesDel")
//    @ApiOperation(value = "用户节点删除")
//    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
//    public ResultJson userNodesDel(HttpServletRequest request, @RequestBody UserNodeUpdateDto userNodeUpdateDto){
//        String token = request.getHeader(tokenHeader);
//        if (token == null) {
//            return ResultJson.failure(ResultCode.UNAUTHORIZED);
//        }
//        UserDetail userDetail = authService.getUserByToken(token);
//        long userid = userDetail.getId();//userid
//        ResultJson resultJson = swarmService.useNodesDel(userNodeUpdateDto,userid);
//        return resultJson;
//    }
//
//
//    @PreAuthorize("hasAnyRole('USER')") // 只能user角色才能访问该方法
//    @PostMapping(value = "/userNodesExcelAppend" ,consumes = "multipart/*" ,headers = "content-type=multipart/form-data")
//    @ApiOperation(value = "节点批量导入")
//    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
//    public ResultJson userNodesDel( @ApiParam(value = "文件",required = true)MultipartFile file,HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        if (token == null) {
//            return ResultJson.failure(ResultCode.UNAUTHORIZED);
//        }
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        UserDetail userDetail = authService.getUserByToken(token);
//        long userid = userDetail.getId();//userid
//        return ResultJson.ok();
//    }





}
