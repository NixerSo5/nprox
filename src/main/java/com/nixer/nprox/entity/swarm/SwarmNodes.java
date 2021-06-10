package com.nixer.nprox.entity.swarm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SwarmNodes {

    @ApiModelProperty(value="标识")
    private Integer id;

    @ApiModelProperty(value="code")
    private String code;

    @ApiModelProperty(value="服务器code")
    private String server_code;

    @ApiModelProperty(value="peer_id")
    private String peer_id;

    @ApiModelProperty(value="类别")
    private String type;

    @ApiModelProperty(value="以太坊地址")
    private String address;

    @ApiModelProperty(value="ip")
    private String ip;

    @ApiModelProperty(value="地区")
    private String area;

    @ApiModelProperty(value="链接节点数")
    private Integer link_nodes;

    @ApiModelProperty(value="状态 0离线 1在线")
    private Integer state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getServer_code() {
        return server_code;
    }

    public void setServer_code(String server_code) {
        this.server_code = server_code;
    }

    public String getPeer_id() {
        return peer_id;
    }

    public void setPeer_id(String peer_id) {
        this.peer_id = peer_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getLink_nodes() {
        return link_nodes;
    }

    public void setLink_nodes(Integer link_nodes) {
        this.link_nodes = link_nodes;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
