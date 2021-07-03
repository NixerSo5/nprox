package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel
public class SwarmNodesDto {

    @ApiModelProperty(value="ID")
    private String code;
    /**
     * 矿机标识
     */
    @ApiModelProperty(value="矿机标识")
    private String server_code;
    /**
     * peerid
     */

    @ApiModelProperty(value="swarm network id")
    private String peer_id;
    /**
     * 类型
     */
    @ApiModelProperty(value="leixing ")
    private String type;
    /**
     * 以太地址
     */
    @ApiModelProperty(value="以太地址")
    private String address;
    /**
     * 节点ip
     */
    @ApiModelProperty(value="ip")
    private String ip;
    /**
     * 区域
     */
    @ApiModelProperty(value="区域")
    private String area;


    @ApiModelProperty(value="所属用户")
    private String owneruser;

    @ApiModelProperty(value="所属用户id")
    private String owneruserid;


    @ApiModelProperty(value="到期时间")
    private String sovertime;


    @ApiModelProperty(value="节点id操作节点用")
    private String node_uid;
    /**
     * 状态0离线  1在线
     */
    @ApiModelProperty(value="状态0离线  1在线")
    private Integer state;

    @ApiModelProperty(value="状态0为  1已")
    private Integer isuse;


    private Date ovetime;

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

    public String getOwneruser() {
        return owneruser;
    }

    public void setOwneruser(String owneruser) {
        this.owneruser = owneruser;
    }

    public String getOwneruserid() {
        return owneruserid;
    }

    public void setOwneruserid(String owneruserid) {
        this.owneruserid = owneruserid;
    }

    public String getSovertime() {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        if(this.ovetime!=null){
            return sdf.format(this.ovetime);
        }
        return sovertime;
    }

    public void setSovertime(String sovertime) {
        this.sovertime = sovertime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getOvetime() {
        return ovetime;
    }

    public void setOvetime(Date ovetime) {
        this.ovetime = ovetime;
    }

    public String getNode_uid() {
        return node_uid;
    }

    public void setNode_uid(String node_uid) {
        this.node_uid = node_uid;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }
}
