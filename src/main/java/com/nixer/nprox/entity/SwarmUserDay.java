package com.nixer.nprox.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SwarmUserDay)实体类
 *
 * @author makejava
 * @since 2021-06-20 14:18:29
 */
public class SwarmUserDay implements Serializable {
    private static final long serialVersionUID = -25511758743504163L;

    private Integer id;

    private Integer userid;

    private Integer cashOut;

    private Long bzz;

    private String date;

    private Date utime;
    /**
     * 日节点数
     */
    private Integer nodeNum;


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

    public Integer getCashOut() {
        return cashOut;
    }

    public void setCashOut(Integer cashOut) {
        this.cashOut = cashOut;
    }

    public Long getBzz() {
        return bzz;
    }

    public void setBzz(Long bzz) {
        this.bzz = bzz;
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

    public Integer getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(Integer nodeNum) {
        this.nodeNum = nodeNum;
    }

}
