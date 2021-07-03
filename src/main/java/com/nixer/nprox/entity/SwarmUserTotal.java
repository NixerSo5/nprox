package com.nixer.nprox.entity;

import java.io.Serializable;

/**
 * (SwarmUserTotal)实体类
 *
 * @author makejava
 * @since 2021-06-20 14:18:38
 */
public class SwarmUserTotal implements Serializable {
    private static final long serialVersionUID = 292243383405077470L;

    private Integer id;

    private Integer userid;

    private Integer totalCashout;

    private Long totalSendBzz;

    private Long totalGetBzz;

    private Long bzz;

    private Integer totalNodeNum;


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

    public Integer getTotalCashout() {
        return totalCashout;
    }

    public void setTotalCashout(Integer totalCashout) {
        this.totalCashout = totalCashout;
    }

    public Long getTotalSendBzz() {
        return totalSendBzz;
    }

    public void setTotalSendBzz(Long totalSendBzz) {
        this.totalSendBzz = totalSendBzz;
    }

    public Long getTotalGetBzz() {
        return totalGetBzz;
    }

    public void setTotalGetBzz(Long totalGetBzz) {
        this.totalGetBzz = totalGetBzz;
    }

    public Long getBzz() {
        return bzz;
    }

    public void setBzz(Long bzz) {
        this.bzz = bzz;
    }

    public Integer getTotalNodeNum() {
        return totalNodeNum;
    }

    public void setTotalNodeNum(Integer totalNodeNum) {
        this.totalNodeNum = totalNodeNum;
    }

}
