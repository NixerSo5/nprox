package com.nixer.nprox.entity.swarm;

import java.io.Serializable;

/**
 * (Agent)实体类
 *
 * @author makejava
 * @since 2021-06-17 22:48:37
 */
public class Agent implements Serializable {
    private static final long serialVersionUID = 818318723653600461L;

    private Integer id;

    private String name;

    private Integer owneruserid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOwneruserid() {
        return owneruserid;
    }

    public void setOwneruserid(Integer owneruserid) {
        this.owneruserid = owneruserid;
    }

}
