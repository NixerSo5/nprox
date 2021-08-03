package com.nixer.nprox.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.io.Serializable;

/**
 * (XchDay)实体类
 *
 * @author makejava
 * @since 2021-07-30 15:23:33
 */
public class XchDay implements Serializable {
    private static final long serialVersionUID = -37969487211457902L;

    private Integer id;

    private String update_date;
    /**
     * 产出
     */
    private BigInteger cashout;

    private Date ctime;

    private Integer nodenum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public BigInteger getCashout() {
        return cashout;
    }

    public void setCashout(BigInteger cashout) {
        this.cashout = cashout;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getNodenum() {
        return nodenum;
    }

    public void setNodenum(Integer nodenum) {
        this.nodenum = nodenum;
    }

}
