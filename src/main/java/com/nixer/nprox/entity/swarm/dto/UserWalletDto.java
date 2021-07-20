package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.math.BigDecimal;

@ApiModel
public class UserWalletDto {


    @ApiModelProperty(value="tokenid")
    private Integer tokenid;

    @ApiModelProperty(value="token的名称")
    private String tokenname;

    @ApiModelProperty(value="token的图标")
    private String tokenimgurl;

    @ApiModelProperty(value="已提现金额long")
    private Long  cashout;

    @ApiModelProperty(value="余额long")
    private Long  balance;

    @ApiModelProperty(value="小数位数")
    private Integer gcd;

    @ApiModelProperty(value="是否激活 大于0的是已激活 ")
    private Integer isactive;

    @ApiModelProperty(value="余额string")
    private String sbllance;


    @ApiModelProperty(value="已提现金额string")
    private String scashout;

    @ApiModelProperty(value="拥有组合数")
    private Double unitnum;


    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }

    public String getTokenname() {
        return tokenname;
    }

    public void setTokenname(String tokenname) {
        this.tokenname = tokenname;
    }

    public String getTokenimgurl() {
        return tokenimgurl;
    }

    public void setTokenimgurl(String tokenimgurl) {
        this.tokenimgurl = tokenimgurl;
    }

    public Long getCashout() {
        return cashout;
    }

    public void setCashout(Long cashout) {
        this.cashout = cashout;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Integer getGcd() {
        return gcd;
    }

    public void setGcd(Integer gcd) {
        this.gcd = gcd;
    }

    public String getSbllance() {
        return castString(this.balance,this.gcd);
    }

    public void setSbllance(String sbllance) {
        this.sbllance = sbllance;
    }

    public String getScashout() {
        return castString(this.cashout,this.gcd);
    }

    public void setScashout(String scashout) {
        this.scashout = scashout;
    }

    public static String castString(long val,int gcd) {
       double x =  Math.pow(10, gcd);
       return new BigDecimal(val/x).divide(new BigDecimal(1),18,BigDecimal.ROUND_HALF_DOWN).toPlainString();
    }

    public Double getUnitnum() {
        return unitnum;
    }

    public void setUnitnum(Double unitnum) {
        this.unitnum = unitnum;
    }

    public static void main(String[] args) {
        System.out.println(castString(200000000000l,18));
    }
}
