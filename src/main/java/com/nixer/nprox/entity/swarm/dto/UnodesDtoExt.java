package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel
public class UnodesDtoExt extends UnodesDto{


    @ApiModelProperty(value="节点用户id")
    private Integer  userid;

    @ApiModelProperty(required = false ,hidden = true)
    private Date overtime;

    @ApiModelProperty(value="结束时间  YYYY-MM-DD")
    private String sovertime;


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    public String getSovertime() {
        return sovertime;
    }

    public void setSovertime(String sovertime) {
        this.sovertime = sovertime;
    }
}
