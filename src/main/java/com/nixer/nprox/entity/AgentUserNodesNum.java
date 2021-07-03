package com.nixer.nprox.entity;

import java.io.Serializable;

/**
 * (AgentUserNodesNum)实体类
 *
 * @author makejava
 * @since 2021-06-19 21:47:30
 */
public class AgentUserNodesNum implements Serializable {
    private static final long serialVersionUID = 941334737774932062L;

    private Integer id;

    private Integer userid;

    private Integer agentid;

    private Integer node_num;


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

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public Integer getNode_num() {
        return node_num;
    }

    public void setNode_num(Integer node_num) {
        this.node_num = node_num;
    }
}
