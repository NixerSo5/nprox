package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class ActiveWalletDto implements Serializable {

    @ApiModelProperty(value="token  的id ")
    private  Integer  tokenid;

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }
}
