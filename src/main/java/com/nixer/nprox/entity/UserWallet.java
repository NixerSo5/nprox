package com.nixer.nprox.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigInteger;

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

    private BigInteger cashout;

    private BigInteger balance;

    private Long unitnum	;


    public Long getUnitnum() {
        return unitnum;
    }

    public void setUnitnum(Long unitnum) {
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
}
