package com.nixer.nprox.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SwarmUserNodes)实体类
 *
 * @author makejava
 * @since 2021-06-20 13:50:32
 */
public class SwarmUserNodes implements Serializable {
    private static final long serialVersionUID = 405443119111938822L;

    private Integer id;

    private Integer userid;

    private String nodeUid;

    private Date overtime;


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

    public String getNodeUid() {
        return nodeUid;
    }

    public void setNodeUid(String nodeUid) {
        this.nodeUid = nodeUid;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

}
