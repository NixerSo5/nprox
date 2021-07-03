package com.nixer.nprox.entity.swarm.dto;

import com.nixer.nprox.entity.common.dto.PageFindDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

@ApiModel
public class NodesFindDto extends PageFindDto {



    @ApiModelProperty(value="0offline  1online  -1all")
    private Integer state;


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
