package com.nixer.nprox.entity.swarm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel
public class SwarmUserDayExt {

    @ApiModelProperty(value="出票数")
    private Integer cash_out;

    @ApiModelProperty(value="bzz数")
    private Long bzz;

    @ApiModelProperty(value="结算日期")
    private String date;

    @ApiModelProperty(value="更新时间")
    private Date utime;

    @ApiModelProperty(value="节点数量")
    private Integer node_num;

    public Integer getCash_out() {
        return cash_out;
    }

    public void setCash_out(Integer cash_out) {
        this.cash_out = cash_out;
    }

    public Long getBzz() {
        return bzz;
    }

    public void setBzz(Long bzz) {
        this.bzz = bzz;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Integer getNode_num() {
        return node_num;
    }

    public void setNode_num(Integer node_num) {
        this.node_num = node_num;
    }
}
