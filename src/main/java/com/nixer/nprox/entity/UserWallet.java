package com.nixer.nprox.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (UserWallet)实体类
 *
 * @author makejava
 * @since 2021-07-06 12:05:47
 */
public class UserWallet implements Serializable {
    private static final long serialVersionUID = -89052295036587504L;

    private Integer id;

    private Integer userid;

    private Integer tokenid;

    private Long cashout;

    private Long balance;

    private Double unitnum	;


    public Double getUnitnum() {
        return unitnum;
    }

    public void setUnitnum(Double unitnum) {
        this.unitnum = unitnum;
    }

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

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
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
}
