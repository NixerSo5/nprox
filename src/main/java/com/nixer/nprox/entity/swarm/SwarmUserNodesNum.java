package com.nixer.nprox.entity.swarm;

import java.io.Serializable;

/**
 * (SwarmUserNodesNum)实体类
 *
 * @author makejava
 * @since 2021-06-10 22:30:06
 */
public class SwarmUserNodesNum implements Serializable {
    private static final long serialVersionUID = 830946849680775727L;

    private Integer id;

    private Integer userid;

    private Integer nodes_num;


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

    public Integer getNodes_num() {
        return nodes_num;
    }

    public void setNodes_num(Integer nodes_num) {
        this.nodes_num = nodes_num;
    }
}
