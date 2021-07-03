package com.nixer.nprox.entity.swarm.dto;

import com.nixer.nprox.entity.common.dto.PageFindDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import sun.font.TrueTypeFont;

@ApiModel
public class AgentUserFindDto extends PageFindDto {

    @ApiModelProperty(value="查找字")
    private String searchtext;


    @ApiModelProperty(value="-1 全部 0为 1正 2冻")
    private Integer state;

    @ApiModelProperty(value="-1 全部")
    private Integer levelid;

    @ApiModelProperty(required = false)
    private Integer agentid;


    public String getSearchtext() {
        return searchtext;
    }

    public void setSearchtext(String searchtext) {
        this.searchtext = searchtext;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLevelid() {
        return levelid;
    }

    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }
}
