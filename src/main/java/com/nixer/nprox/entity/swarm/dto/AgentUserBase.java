package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AgentUserBase {


    @ApiModelProperty(value="联系方式")
    private String phone;

    @ApiModelProperty(value="备注")
    private String note;
    /**
     * 0未审核  1正常   2冻结
     */
    @ApiModelProperty(value="0未审核  1正常   2冻结")
    private Integer state;

    @ApiModelProperty(value="需求节点数量")
    private Integer nodeNum;

    @ApiModelProperty(value="百分比")
    private Integer charges;

    @ApiModelProperty(value="0自动 1手动")
    private Integer viptype;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(Integer nodeNum) {
        this.nodeNum = nodeNum;
    }

    public Integer getCharges() {
        return charges;
    }

    public void setCharges(Integer charges) {
        this.charges = charges;
    }

    public Integer getViptype() {
        return viptype;
    }

    public void setViptype(Integer viptype) {
        this.viptype = viptype;
    }

}
