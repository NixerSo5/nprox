package com.nixer.nprox.entity;

import java.io.Serializable;

/**
 * (SwarmUserNodesNum)实体类
 *
 * @author makejava
 * @since 2021-06-20 14:18:16
 */
public class SwarmUserNodesNum implements Serializable {
    private static final long serialVersionUID = 562929870151668437L;

    private Integer id;

    private Integer userid;

    private Integer nodesNum;


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

    public Integer getNodesNum() {
        return nodesNum;
    }

    public void setNodesNum(Integer nodesNum) {
        this.nodesNum = nodesNum;
    }

}
