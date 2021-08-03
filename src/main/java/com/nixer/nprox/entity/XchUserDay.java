package com.nixer.nprox.entity;

import java.math.BigInteger;
import java.util.Date;
import java.io.Serializable;

/**
 * (XchUserDay)实体类
 *
 * @author makejava
 * @since 2021-07-30 15:40:28
 */
public class XchUserDay implements Serializable {
    private static final long serialVersionUID = -45973908780856028L;

    private Integer id;

    private Integer userid;

    private BigInteger cashout;

    private String date;

    private Date utime;
    /**
     * 日节点数
     */
    private Integer nodenum;

    private String xchjson;


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



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public BigInteger getCashout() {
        return cashout;
    }

    public void setCashout(BigInteger cashout) {
        this.cashout = cashout;
    }

    public Integer getNodenum() {
        return nodenum;
    }

    public void setNodenum(Integer nodenum) {
        this.nodenum = nodenum;
    }

    public String getXchjson() {
        return xchjson;
    }

    public void setXchjson(String xchjson) {
        this.xchjson = xchjson;
    }

}
