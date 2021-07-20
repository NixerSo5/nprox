package com.nixer.nprox.entity.swarm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * (UserOrder)实体类
 *
 * @author makejava
 * @since 2021-06-16 22:34:55
 */
@ApiModel
public class UserOrder implements Serializable {
    private static final long serialVersionUID = -61142420300396522L;

    @ApiModelProperty(value="标识")
    private Integer id;

    @ApiModelProperty(value="标识")
    private Integer userid;
    /**
     * 1提现
     */
    @ApiModelProperty(value="业务类型 1提现")
    private Integer serviceType;

    @ApiModelProperty(value="创建时间")
    private Date date;
    /**
     * 币种
     */
    @ApiModelProperty(value="币种")
    private String currencyType;

    @ApiModelProperty(value="数量")
    private BigDecimal quantity;

    @ApiModelProperty(value="手续费")
    private BigDecimal serviceCharges;

    @ApiModelProperty(value="收款地址")
    private String withdrawAddress;



    @ApiModelProperty(value="数量显示")
    private String quantitys;

    @ApiModelProperty(value="手续费显示")
    private String serviceChargess;

    private Integer tokenid;

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }

    public String getQuantitys() {
        if(this.quantity!=null){
         return this.quantity.toPlainString();
        }
        return "";
    }

    public void setQuantitys(String quantitys) {
        this.quantitys = quantitys;
    }

    public String getServiceChargess() {
        if(this.serviceCharges!=null){
            return this.serviceCharges.toPlainString();
        }
        return "";
    }

    public void setServiceChargess(String serviceChargess) {
        this.serviceChargess = serviceChargess;
    }

    @ApiModelProperty(value="备注")
    private String remarks;
    /**
     * 0pending 1faill  2ok
     */
    @ApiModelProperty(value="0pending 1faill  2ok")
    private Integer status;


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

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }



    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }



    public String getWithdrawAddress() {
        return withdrawAddress;
    }

    public void setWithdrawAddress(String withdrawAddress) {
        this.withdrawAddress = withdrawAddress;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(BigDecimal serviceCharges) {
        this.serviceCharges = serviceCharges;
    }
}
