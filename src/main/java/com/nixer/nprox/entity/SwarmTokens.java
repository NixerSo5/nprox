package com.nixer.nprox.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (SwarmTokens)实体类
 *
 * @author makejava
 * @since 2021-07-06 11:55:53
 */
@ApiModel
public class SwarmTokens implements Serializable {
    private static final long serialVersionUID = -55746700195840450L;

    @ApiModelProperty(value = "id 标识")
    private Integer id;

    @ApiModelProperty(value="名字")
    private String tokenname;

    @ApiModelProperty(value="图标url")
    private String tokenimgurl;

    @ApiModelProperty(value="最小提现金额")
    private Long cashoutlimit;

    @ApiModelProperty(value="最小运行金额")
    private Long basenodelimit;

    @ApiModelProperty(value="平台抽成比例")
    private Long cut;

    @ApiModelProperty(value="小数位数")
    private Integer gcd;



    @ApiModelProperty(value="单位")
    private String unit;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTokenname() {
        return tokenname;
    }

    public void setTokenname(String tokenname) {
        this.tokenname = tokenname;
    }

    public String getTokenimgurl() {
        return tokenimgurl;
    }

    public void setTokenimgurl(String tokenimgurl) {
        this.tokenimgurl = tokenimgurl;
    }

    public Long getCashoutlimit() {
        return cashoutlimit;
    }

    public void setCashoutlimit(Long cashoutlimit) {
        this.cashoutlimit = cashoutlimit;
    }

    public Long getBasenodelimit() {
        return basenodelimit;
    }

    public void setBasenodelimit(Long basenodelimit) {
        this.basenodelimit = basenodelimit;
    }

    public Long getCut() {
        return cut;
    }

    public void setCut(Long cut) {
        this.cut = cut;
    }

    public Integer getGcd() {
        return gcd;
    }

    public void setGcd(Integer gcd) {
        this.gcd = gcd;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
