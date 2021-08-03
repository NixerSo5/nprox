package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.math.BigDecimal;
import java.math.BigInteger;

@ApiModel
public class UserWalletDto {


    @ApiModelProperty(value="tokenid")
    private Integer tokenid;

    @ApiModelProperty(value="token的名称")
    private String tokenname;

    @ApiModelProperty(value="token的图标")
    private String tokenimgurl;

    @ApiModelProperty(value="已提现金额long")
    private BigInteger cashout;

    @ApiModelProperty(value="余额long")
    private BigInteger  balance;

    @ApiModelProperty(value="小数位数")
    private Integer gcd;

    @ApiModelProperty(value="是否激活 大于0的是已激活 ")
    private Integer isactive;

    @ApiModelProperty(value="余额string")
    private String sbllance;


    @ApiModelProperty(value="已提现金额string")
    private String scashout;

    @ApiModelProperty(value="拥有组合数")
    private String unitnum;


    @ApiModelProperty(hidden = true)
    private Long tunitnum;

    @ApiModelProperty(hidden = true)
    private Integer unitgcd;


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

    public BigInteger getCashout() {
        return cashout;
    }

    public void setCashout(BigInteger cashout) {
        this.cashout = cashout;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
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

    public static String castString(BigInteger val,int gcd) {
       double x =  Math.pow(10, gcd);
       return new BigDecimal(val).divide(new BigDecimal(x),18,BigDecimal.ROUND_HALF_DOWN).toPlainString();
    }

    public String getUnitnum() {
        if(unitgcd!=0){
            BigDecimal nb = new BigDecimal(this.tunitnum).divide(new BigDecimal(unitgcd)).divide(new BigDecimal(1),2,
                    BigDecimal.ROUND_HALF_UP);
            return nb.toPlainString();
        }else {
            return this.tunitnum.toString();
        }
    }

    public void setUnitnum(String unitnum) {
        this.unitnum = unitnum;
    }

    public Long getTunitnum() {
        return tunitnum;
    }

    public void setTunitnum(Long tunitnum) {
        this.tunitnum = tunitnum;
    }

    public Integer getUnitgcd() {
        return unitgcd;
    }

    public void setUnitgcd(Integer unitgcd) {
        this.unitgcd = unitgcd;
    }

    public static void main(String[] args) {
        System.out.println(new BigDecimal(new BigInteger("1")).divide(new BigDecimal(new BigInteger(
                "1000000000000000000000000")),18,BigDecimal.ROUND_HALF_UP).toPlainString());

        BigDecimal nb = new BigDecimal(1800).divide(new BigDecimal(1024)).divide(new BigDecimal(1),2,BigDecimal.ROUND_CEILING);
        System.out.println(nb.toPlainString());


    }
}
