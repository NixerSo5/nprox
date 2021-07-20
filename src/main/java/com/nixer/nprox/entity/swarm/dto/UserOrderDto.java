package com.nixer.nprox.entity.swarm.dto;//

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel
public class UserOrderDto {


    @ApiModelProperty(value="收款地址")
    private String WithdrawAddress;// '收款地址',

    @ApiModelProperty(value="提取数量")
    private BigDecimal WithdrawNum;// '提取数量',

    @ApiModelProperty(value="0默认到账  1快速到账")
    private String  usegas;// '0默认到账  1快速到账',

    @ApiModelProperty(value="备注")
    private String  remarks;// '备注',

    @ApiModelProperty(value="币种id")
    private String  tokenid;


    @ApiModelProperty(hidden = true)
    private String doipaddr;


    public String getWithdrawAddress() {
        return WithdrawAddress;
    }

    public void setWithdrawAddress(String withdrawAddress) {
        WithdrawAddress = withdrawAddress;
    }

    public BigDecimal getWithdrawNum() {
        return WithdrawNum;
    }

    public void setWithdrawNum(BigDecimal withdrawNum) {
        WithdrawNum = withdrawNum;
    }

    public String getUsegas() {
        return usegas;
    }

    public void setUsegas(String usegas) {
        this.usegas = usegas;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDoipaddr() {
        return doipaddr;
    }

    public void setDoipaddr(String doipaddr) {
        this.doipaddr = doipaddr;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }
}
