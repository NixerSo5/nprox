package com.nixer.nprox.entity.swarm.dto;

import io.swagger.models.auth.In;

public class NodeNumDto {

    private Integer num;
    private Integer isuse;


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }
}
