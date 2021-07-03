package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AgentLevelDto {


    @ApiModelProperty(value="等级名称")
    private String level;

    @ApiModelProperty(value="节点数量")
    private Integer need_nodes;

    @ApiModelProperty(value="比例 最小能消于8")
    private Integer charges;


    public Integer getNeed_nodes() {
        return need_nodes;
    }

    public void setNeed_nodes(Integer need_nodes) {
        this.need_nodes = need_nodes;
    }

    public Integer getCharges() {
        return charges;
    }

    public void setCharges(Integer charges) {
        this.charges = charges;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
