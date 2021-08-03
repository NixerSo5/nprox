package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModelProperty;

public class XchLineDataDto {


    @ApiModelProperty(value="日期")
    private String date;

    @ApiModelProperty(value="金额")
    private String cashout;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCashout() {
        return cashout;
    }

    public void setCashout(String cashout) {
        this.cashout = cashout;
    }
}
