package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LineDateDto {

    @ApiModelProperty(value="节点数量")
    private String date;

    @ApiModelProperty(value="出票数")
    private String cashout;

    @ApiModelProperty(value="节点数量")
    private String cheques;

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

    public String getCheques() {
        return cheques;
    }

    public void setCheques(String cheques) {
        this.cheques = cheques;
    }
}
