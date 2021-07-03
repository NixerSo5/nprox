package com.nixer.nprox.entity.swarm;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * (AgentOrder)实体类
 *
 * @author makejava
 * @since 2021-06-17 22:43:33
 */
public class AgentOrder implements Serializable {
    private static final long serialVersionUID = 468602593651428025L;

    private Integer id;

    private Integer orderid;

    private Integer userid;

    private Long charges;

    private BigDecimal orderbzz;

    private BigDecimal getbzz;

    private Date ctime;
    /**
     * 0 未结算  1已结算
     */
    private Integer issend;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Long getCharges() {
        return charges;
    }

    public void setCharges(Long charges) {
        this.charges = charges;
    }

    public BigDecimal getOrderbzz() {
        return orderbzz;
    }

    public void setOrderbzz(BigDecimal orderbzz) {
        this.orderbzz = orderbzz;
    }

    public BigDecimal getGetbzz() {
        return getbzz;
    }

    public void setGetbzz(BigDecimal getbzz) {
        this.getbzz = getbzz;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getIssend() {
        return issend;
    }

    public void setIssend(Integer issend) {
        this.issend = issend;
    }

}
