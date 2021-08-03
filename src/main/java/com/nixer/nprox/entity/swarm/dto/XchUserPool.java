package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigInteger;

@ApiModel
public class XchUserPool implements Serializable {


    @ApiModelProperty(value="总收益")
    private String totalcash;

    @ApiModelProperty(value="农田数量")
    private String framnum;

    @ApiModelProperty(value="农田大小")
    private String framsize;

    @ApiModelProperty(value="昨日产出")
    private String yesterdaycash;

    @ApiModelProperty(value="昨日本地产出")
    private String yesterdaylocal;


    public String getTotalcash() {
        return totalcash;
    }

    public void setTotalcash(String totalcash) {
        this.totalcash = totalcash;
    }

    public String getFramnum() {
        return framnum;
    }

    public void setFramnum(String framnum) {
        this.framnum = framnum;
    }

    public String getFramsize() {
        return framsize;
    }

    public void setFramsize(String framsize) {
        this.framsize = framsize;
    }

    public String getYesterdaycash() {
        return yesterdaycash;
    }

    public void setYesterdaycash(String yesterdaycash) {
        this.yesterdaycash = yesterdaycash;
    }

    public String getYesterdaylocal() {
        return yesterdaylocal;
    }

    public void setYesterdaylocal(String yesterdaylocal) {
        this.yesterdaylocal = yesterdaylocal;
    }
}
