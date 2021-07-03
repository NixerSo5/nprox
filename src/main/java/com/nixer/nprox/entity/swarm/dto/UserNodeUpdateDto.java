package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserNodeUpdateDto {

    @ApiModelProperty(value = "地址uid")
    private String  node_uid;

    public String getNode_uid() {
        return node_uid;
    }

    public void setNode_uid(String node_uid) {
        this.node_uid = node_uid;
    }
}
