package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


@ApiModel
public class SwarmUserTotalDto {

    @ApiModelProperty(value="总出票")
    private Integer total_cashout;

    @ApiModelProperty(value="总发出bzz")
    private BigDecimal total_send_bzz;

    @ApiModelProperty(value="总获取bzz")
    private BigDecimal total_get_bzz;


    @ApiModelProperty(value="bzz")
    private BigDecimal bzz;

    @ApiModelProperty(value="总节点数")
    private Integer    total_node_num;


    public Integer getTotal_cashout() {
        return total_cashout;
    }

    public void setTotal_cashout(Integer total_cashout) {
        this.total_cashout = total_cashout;
    }

    public BigDecimal getTotal_send_bzz() {
        return total_send_bzz;
    }

    public void setTotal_send_bzz(BigDecimal total_send_bzz) {
        this.total_send_bzz = total_send_bzz;
    }

    public BigDecimal getTotal_get_bzz() {
        return total_get_bzz;
    }

    public void setTotal_get_bzz(BigDecimal total_get_bzz) {
        this.total_get_bzz = total_get_bzz;
    }

    public BigDecimal getBzz() {
        return bzz;
    }

    public void setBzz(BigDecimal bzz) {
        this.bzz = bzz;
    }

    public Integer getTotal_node_num() {
        return total_node_num;
    }

    public void setTotal_node_num(Integer total_node_num) {
        this.total_node_num = total_node_num;
    }


}
