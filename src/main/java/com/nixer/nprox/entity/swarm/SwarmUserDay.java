package com.nixer.nprox.entity.swarm;

import java.math.BigDecimal;

public class SwarmUserDay extends SwarmUserDayExt{

    private Integer id;
    private Integer userid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
