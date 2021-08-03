package com.nixer.nprox.entity.swarm.dto;

import com.nixer.nprox.entity.swarm.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BzzUserPoolUnit {// bzz

    @ApiModelProperty(value="用户总计")
    private SwarmUserTotalDto swarmUserTotal;

    @ApiModelProperty(value="用户今日")
    private SwarmUserDayDto swarmUserDay;

    @ApiModelProperty(value="计算结果集")
    private SwarmUserExp swarmUserExp;

    @ApiModelProperty(value="节点状态")
    private UserNodesStateDto userNodesStateDto;

    public SwarmUserTotalDto getSwarmUserTotal() {
        return swarmUserTotal;
    }

    public void setSwarmUserTotal(SwarmUserTotalDto swarmUserTotal) {
        this.swarmUserTotal = swarmUserTotal;
    }

    public SwarmUserDayDto getSwarmUserDay() {
        return swarmUserDay;
    }

    public void setSwarmUserDay(SwarmUserDayDto swarmUserDay) {
        this.swarmUserDay = swarmUserDay;
    }

    public SwarmUserExp getSwarmUserExp() {
        return swarmUserExp;
    }

    public void setSwarmUserExp(SwarmUserExp swarmUserExp) {
        this.swarmUserExp = swarmUserExp;
    }

    public UserNodesStateDto getUserNodesStateDto() {
        return userNodesStateDto;
    }

    public void setUserNodesStateDto(UserNodesStateDto userNodesStateDto) {
        this.userNodesStateDto = userNodesStateDto;
    }
}
