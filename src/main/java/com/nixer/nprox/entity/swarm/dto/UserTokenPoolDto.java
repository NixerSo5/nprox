package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel
public class UserTokenPoolDto implements Serializable {

    @ApiModelProperty(value="bzz矿池概览")
    private BzzUserPoolUnit bzzUserPool;

    @ApiModelProperty(value="xch矿池概览")
    private XchUserPool  xchUserPool;


    public BzzUserPoolUnit getBzzUserPool() {
        return bzzUserPool;
    }

    public void setBzzUserPool(BzzUserPoolUnit bzzUserPool) {
        this.bzzUserPool = bzzUserPool;
    }

    public XchUserPool getXchUserPool() {
        return xchUserPool;
    }

    public void setXchUserPool(XchUserPool xchUserPool) {
        this.xchUserPool = xchUserPool;
    }
}
