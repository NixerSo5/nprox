package com.nixer.nprox.entity.swarm;

import java.math.BigDecimal;

public class SwarmDay {

    private Integer id;
    private String update_date;
    private Integer cashout;
    private BigDecimal bzzout;
    private Integer nodes_num;


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

    public Integer getCashout() {
        return cashout;
    }

    public void setCashout(Integer cashout) {
        this.cashout = cashout;
    }

    public BigDecimal getBzzout() {
        return bzzout;
    }

    public void setBzzout(BigDecimal bzzout) {
        this.bzzout = bzzout;
    }

    public Integer getNodes_num() {
        return nodes_num;
    }

    public void setNodes_num(Integer nodes_num) {
        this.nodes_num = nodes_num;
    }
}
