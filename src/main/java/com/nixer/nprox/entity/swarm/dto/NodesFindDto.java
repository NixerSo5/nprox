package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

@ApiModel
public class NodesFindDto {

    @ApiModelProperty(value="当前页")
    private Integer index;

    @ApiModelProperty(value="页面size")
    private Integer size;

    @ApiModelProperty(value="0offline  1online  -1all")
    private Integer state;


    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
