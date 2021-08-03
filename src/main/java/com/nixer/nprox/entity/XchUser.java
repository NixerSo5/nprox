package com.nixer.nprox.entity;

import java.io.Serializable;

/**
 * (XchUser)实体类
 *
 * @author makejava
 * @since 2021-07-30 16:50:49
 */
public class XchUser implements Serializable {
    private static final long serialVersionUID = 768273757793508833L;

    private Integer id;

    private Integer userid;
    /**
     * 农田数量
     */
    private Long framnum;
    /**
     * 农田json
     */
    private String framjson;

    private Long framsize;


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

    public Long getFramnum() {
        return framnum;
    }

    public Long getFramsize() {
        return framsize;
    }

    public void setFramsize(Long framsize) {
        this.framsize = framsize;
    }

    public void setFramnum(Long framnum) {
        this.framnum = framnum;
    }

    public String getFramjson() {
        return framjson;
    }

    public void setFramjson(String framjson) {
        this.framjson = framjson;
    }

}
