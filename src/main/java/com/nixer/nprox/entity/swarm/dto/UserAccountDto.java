package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel
public class UserAccountDto {


    @ApiModelProperty(value="地址")
    private String  username;

    @ApiModelProperty(value="地址")
    private String  address;

    @ApiModelProperty(value="nickname")
    private String  nickname;

    @ApiModelProperty(value="头像")
    private String  imgurl;

    @ApiModelProperty(value="email")
    private String     email;

    @ApiModelProperty(value="phone")
    private String      phone;



    @ApiModelProperty(value="total_cashout")
    private Integer     total_cashout;

    @ApiModelProperty(value="total_get_bzz")
    private BigDecimal total_get_bzz;

    @ApiModelProperty(value="total_send_bzz")
    private BigDecimal       total_send_bzz;

    @ApiModelProperty(value="bzz")
    private BigDecimal  bzz;


    @ApiModelProperty(value="cbzz 可提现金额")
    private BigDecimal cbzz;

    @ApiModelProperty(value="cashout_bzz 已提现金额")
    private BigDecimal cashout_bzz;

    @ApiModelProperty(value="dai余额")
    private BigDecimal       cash_dai;





    private String lastip;

    private String sctime;

    private String sutime;

    private Date ctime;

    private Date utime;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    public String getSctime() {
        if(this.ctime!=null){
            return  simpleDateFormat.format(this.ctime);
        }
        return sctime;
    }

    public void setSctime(String sctime) {
        this.sctime = sctime;
    }

    public String getSutime() {
        if(this.utime!=null){
            return  simpleDateFormat.format(this.utime);
        }
        return sutime;
    }

    public void setSutime(String sutime) {
        this.sutime = sutime;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Integer getTotal_cashout() {
        return total_cashout;
    }

    public void setTotal_cashout(Integer total_cashout) {
        this.total_cashout = total_cashout;
    }

    public BigDecimal getTotal_get_bzz() {
        return total_get_bzz;
    }

    public void setTotal_get_bzz(BigDecimal total_get_bzz) {
        this.total_get_bzz = total_get_bzz;
    }

    public BigDecimal getTotal_send_bzz() {
        return total_send_bzz;
    }

    public void setTotal_send_bzz(BigDecimal total_send_bzz) {
        this.total_send_bzz = total_send_bzz;
    }

    public BigDecimal getBzz() {
        return bzz;
    }

    public void setBzz(BigDecimal bzz) {
        this.bzz = bzz;
    }

    public BigDecimal getCbzz() {
        return cbzz;
    }

    public void setCbzz(BigDecimal cbzz) {
        this.cbzz = cbzz;
    }

    public BigDecimal getCashout_bzz() {
        return cashout_bzz;
    }

    public void setCashout_bzz(BigDecimal cashout_bzz) {
        this.cashout_bzz = cashout_bzz;
    }

    public BigDecimal getCash_dai() {
        return cash_dai;
    }

    public void setCash_dai(BigDecimal cash_dai) {
        this.cash_dai = cash_dai;
    }
}
