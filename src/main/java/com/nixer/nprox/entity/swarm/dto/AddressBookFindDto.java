package com.nixer.nprox.entity.swarm.dto;

import com.nixer.nprox.entity.common.dto.PageFindDto;
import io.swagger.annotations.ApiModelProperty;

public class AddressBookFindDto extends PageFindDto {

    @ApiModelProperty(value="查找token类型 ")
    private Integer tokenid;

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }
}
