package com.nixer.nprox.controller.swarm;

import com.nixer.nprox.entity.SwarmTokenTotal;
import com.nixer.nprox.entity.common.UserDetail;
import com.nixer.nprox.entity.common.dto.SinglePramDto;
import com.nixer.nprox.entity.swarm.dto.LineDateDto;
import com.nixer.nprox.entity.swarm.dto.UserTokenLineDto;
import com.nixer.nprox.entity.swarm.dto.UserTokenPoolDto;
import com.nixer.nprox.entity.swarm.dto.WalletInfoDto;
import com.nixer.nprox.service.auth.AuthService;
import com.nixer.nprox.service.swarm.SwarmService;
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
import java.util.List;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private SwarmService swarmService;
    @Autowired
    private AuthService authService;


    //

    @PreAuthorize("hasAnyRole('USER')") // 只能user角色才能访问该方法
    @PostMapping("/userTokenPreview")
    @ApiOperation(value = "获取用户币种当前矿池状态 doid为tokenid")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<UserTokenPoolDto> userTokenPreview(HttpServletRequest request, @RequestBody SinglePramDto singlePramDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        UserTokenPoolDto userPoolUnit = swarmService.userTokenPreview(userid,singlePramDto);
        return ResultJson.ok(userPoolUnit);
    }


    //折线图
    //柱状图
    @PreAuthorize("hasAnyRole('USER')") // 只能user角色才能访问该方法
    @PostMapping("/userTokenPoolStateLine")
    @ApiOperation(value = "获取用户七日矿池状态/暂定7日后续可以修改为访问值 doid为tokenid")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")})
    public ResultJson<UserTokenLineDto> userTokenPoolStateLine(HttpServletRequest request,
                                                               @RequestBody SinglePramDto singlePramDto){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        long userid = userDetail.getId();//userid
        UserTokenLineDto userTokenLineDto = swarmService.userTokenPoolStateLine(userid,singlePramDto);
        return ResultJson.ok(userTokenLineDto);
    }
}
