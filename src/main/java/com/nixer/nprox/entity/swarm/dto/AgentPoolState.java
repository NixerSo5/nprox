package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AgentPoolState {

    @ApiModelProperty(value="节点数量")
    private Integer total_num;

    @ApiModelProperty(value="活跃")
    private Integer active_num;

    @ApiModelProperty(value="离线")
    private Integer offline_num;

    @ApiModelProperty(value="分配")
    private Integer send_num;

    @ApiModelProperty(value="未分配")
    private Integer rest_num;


    public Integer getTotal_num() {
        return total_num;
    }

    public void setTotal_num(Integer total_num) {
        this.total_num = total_num;
    }

    public Integer getActive_num() {
        return active_num;
    }

    public void setActive_num(Integer active_num) {
        this.active_num = active_num;
    }

    public Integer getOffline_num() {
        return offline_num;
    }

    public void setOffline_num(Integer offline_num) {
        this.offline_num = offline_num;
    }

    public Integer getSend_num() {
        return send_num;
    }

    public void setSend_num(Integer send_num) {
        this.send_num = send_num;
    }

    public Integer getRest_num() {
        return rest_num;
    }

    public void setRest_num(Integer rest_num) {
        this.rest_num = rest_num;
    }
}
