package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

@ApiModel
public class UserJoinAgentDto {

    @ApiModelProperty(value="代理商id")
    private Integer agentid;

    @ApiModelProperty(value="联系方式")
    private String phone;

    @ApiModelProperty(value="需求节点数")
    private  Integer  node_num;

    @ApiModelProperty(value="备注")
    private String note;


    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNode_num() {
        return node_num;
    }

    public void setNode_num(Integer node_num) {
        this.node_num = node_num;
    }
}
