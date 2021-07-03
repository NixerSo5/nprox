package com.nixer.nprox.entity;

import java.io.Serializable;

/**
 * (AgentLevel)实体类
 *
 * @author makejava
 * @since 2021-06-19 11:55:58
 */
public class AgentLevel implements Serializable {
    private static final long serialVersionUID = -33456641301381159L;

    private Integer id;

    private Integer agentid;
    /**
     * 等级
     */
    private String level;


    private Integer need_nodes;
    /**
     * 百分比
     */
    private Integer charges;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getNeed_nodes() {
        return need_nodes;
    }

    public void setNeed_nodes(Integer need_nodes) {
        this.need_nodes = need_nodes;
    }

    public Integer getCharges() {
        return charges;
    }

    public void setCharges(Integer charges) {
        this.charges = charges;
    }

}
