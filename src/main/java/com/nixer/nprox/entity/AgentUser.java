package com.nixer.nprox.entity;

import com.nixer.nprox.entity.swarm.dto.AgentUserBase;

import java.util.Date;
import java.io.Serializable;

/**
 * (AgentUser)实体类
 *
 * @author makejava
 * @since 2021-06-19 17:29:57
 */
public class AgentUser extends AgentUserBase implements Serializable {
    private static final long serialVersionUID = 725314602758998088L;

    private Integer id;

    private Integer userid;

    private Integer agentid;

    private Integer levelid;

    private Date ctime;


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


    public Integer getLevelid() {
        return levelid;
    }

    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
    }


    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

}
