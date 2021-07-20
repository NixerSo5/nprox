package com.nixer.nprox.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * (SwarmTokenTotal)实体类
 *
 * @author makejava
 * @since 2021-07-15 11:43:28
 */
@ApiModel
public class SwarmTokenTotal implements Serializable {
    private static final long serialVersionUID = -47652546458340981L;
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(hidden = true)
    private Integer tokenid;

    @ApiModelProperty(value="更新时间")
    private Date utime;

    @ApiModelProperty(value="昨日均产量")
    private BigDecimal yesterdayB;

    @ApiModelProperty(value="今日均产量")
    private BigDecimal todayB;


    @ApiModelProperty(hidden = true)
    private Long yesterday;

    @ApiModelProperty(hidden = true)
    private Long today;

    @ApiModelProperty(hidden = true)
    private Long total;

    @ApiModelProperty(hidden = true)
    private Integer gcd;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Long getYesterday() {
        return yesterday;
    }

    public void setYesterday(Long yesterday) {
        this.yesterday = yesterday;
    }

    public Long getToday() {
        return today;
    }

    public void setToday(Long today) {
        this.today = today;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getGcd() {
        return gcd;
    }

    public void setGcd(Integer gcd) {
        this.gcd = gcd;
    }

    public BigDecimal getYesterdayB() {
        if(this.yesterday !=null){
            return new BigDecimal(this.yesterday).divide(new BigDecimal(Math.pow(10, this.gcd)));
        }
        return new BigDecimal(0);
    }

    public void setYesterdayB(BigDecimal yesterdayB) {
        this.yesterdayB = yesterdayB;
    }

    public BigDecimal getTodayB() {
        if(this.today !=null){
            return new BigDecimal(this.today).divide(new BigDecimal(Math.pow(10, this.gcd)));
        }
        return new BigDecimal(0);
    }

    public void setTodayB(BigDecimal todayB) {
        this.todayB = todayB;
    }

    public static String castString(long val, int gcd) {
        double x =  Math.pow(10, gcd);
        return String.valueOf(new BigDecimal(val/x));
    }

}
