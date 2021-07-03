package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.util.Date;
import java.util.List;

@ApiModel
public class UnodesDto {


    @ApiModelProperty(value="节点列表")
    private List<NodesUidDto> nodesUidDtoList;


    public List<NodesUidDto> getNodesUidDtoList() {
        return nodesUidDtoList;
    }

    public void setNodesUidDtoList(List<NodesUidDto> nodesUidDtoList) {
        this.nodesUidDtoList = nodesUidDtoList;
    }
}
