package com.nixer.nprox.entity;

import java.io.Serializable;

/**
 * (AgentNodes)实体类
 *
 * @author makejava
 * @since 2021-06-18 12:00:38
 */
public class AgentNodes implements Serializable {
    private static final long serialVersionUID = 860611279521307190L;

    private Integer id;

    private Integer agentid;

    private String node_uid;
    /**
     * 0 为  1已
     */
    private Integer isuse;


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

    public String getNode_uid() {
        return node_uid;
    }

    public void setNode_uid(String node_uid) {
        this.node_uid = node_uid;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

}
