package com.nixer.nprox.entity.swarm.pool;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//{"bzz": "1845.13", "gas": "128", "node_num": "1560"}
@ApiModel
public class PoolConfig {

    @ApiModelProperty(value="节点数量")
    private String node_num;

    @ApiModelProperty(value="gas费用")
    private String gas;

    @ApiModelProperty(value="gas最小费用")
    private String gasmin;

    @ApiModelProperty(value="bzz价格")
    private String bzz;

    public String getNode_num() {
        return node_num;
    }

    public void setNode_num(String node_num) {
        this.node_num = node_num;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getBzz() {
        return bzz;
    }

    public void setBzz(String bzz) {
        this.bzz = bzz;
    }

    public String getGasmin() {
        return gasmin;
    }

    public void setGasmin(String gasmin) {
        this.gasmin = gasmin;
    }
}
