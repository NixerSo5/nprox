package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class NodesUidDto {

    @ApiModelProperty(value="节点id")
    private String node_uid;

    public String getNode_uid() {
        return node_uid;
    }

    public void setNode_uid(String node_uid) {
        this.node_uid = node_uid;
    }
}
