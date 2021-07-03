package com.nixer.nprox.entity.swarm.dto;

import com.nixer.nprox.entity.common.dto.PageFindDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AgentNodesFindDto extends PageFindDto {


    @ApiModelProperty(value="查找字")
    private String  searchtext;

    @ApiModelProperty(value="地区")
    private String   area;

    @ApiModelProperty(value="-1 全部 0未分配 1已分配")
    private Integer   isuse;

    @ApiModelProperty(hidden = true)
    private Integer  agentid;

    public String getSearchtext() {
        return searchtext;
    }

    public void setSearchtext(String searchtext) {
        this.searchtext = searchtext;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }
}
