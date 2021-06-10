package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SwarmDayDto {

  @ApiModelProperty(value="节点数量")
  private String node_num;

  @ApiModelProperty(value="日均出票")
  private String cashout_day;

  @ApiModelProperty(value="日均收益")
  private String bzz_day;

  @ApiModelProperty(value="单节点出票")
  private String single_node_cashout;

  @ApiModelProperty(value="单节点收益")
  private String single_node_profit;

  @ApiModelProperty(value="bzz价格")
  private String bzz;

  public String getNode_num() {
    return node_num;
  }

  public void setNode_num(String node_num) {
    this.node_num = node_num;
  }

  public String getCashout_day() {
    return cashout_day;
  }

  public void setCashout_day(String cashout_day) {
    this.cashout_day = cashout_day;
  }

  public String getBzz_day() {
    return bzz_day;
  }

  public void setBzz_day(String bzz_day) {
    this.bzz_day = bzz_day;
  }

  public String getSingle_node_cashout() {
    return single_node_cashout;
  }

  public void setSingle_node_cashout(String single_node_cashout) {
    this.single_node_cashout = single_node_cashout;
  }

  public String getSingle_node_profit() {
    return single_node_profit;
  }

  public void setSingle_node_profit(String single_node_profit) {
    this.single_node_profit = single_node_profit;
  }

  public String getBzz() {
    return bzz;
  }

  public void setBzz(String bzz) {
    this.bzz = bzz;
  }
}
