package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModelProperty;

public class AgentUserUpdateDto {

    @ApiModelProperty(value="用户id")
    private String userid;

    @ApiModelProperty(value="联系方式")
    private String phone;

    @ApiModelProperty(value="备注")
    private String note;

    @ApiModelProperty(value="需求节点数量")
    private Integer nodeNum;

    @ApiModelProperty(value="等级id")
    private Integer levelid;

    @ApiModelProperty(value="0自动 1手动")
    private Integer viptype;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public Integer getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(Integer nodeNum) {
        this.nodeNum = nodeNum;
    }

    public Integer getLevelid() {
        return levelid;
    }

    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
    }

    public Integer getViptype() {
        return viptype;
    }

    public void setViptype(Integer viptype) {
        this.viptype = viptype;
    }
}
