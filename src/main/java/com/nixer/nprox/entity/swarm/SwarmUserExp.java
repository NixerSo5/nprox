package com.nixer.nprox.entity.swarm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel
public class SwarmUserExp {


    @ApiModelProperty(value="用户今日出票")
    private BigDecimal user_day_cashout;

    @ApiModelProperty(value="用户今日收入")
    private BigDecimal user_day_bzz;

    public BigDecimal getUser_day_cashout() {
        return user_day_cashout;
    }

    public void setUser_day_cashout(BigDecimal user_day_cashout) {
        this.user_day_cashout = user_day_cashout;
    }

    public BigDecimal getUser_day_bzz() {
        return user_day_bzz;
    }

    public void setUser_day_bzz(BigDecimal user_day_bzz) {
        this.user_day_bzz = user_day_bzz;
    }
}
