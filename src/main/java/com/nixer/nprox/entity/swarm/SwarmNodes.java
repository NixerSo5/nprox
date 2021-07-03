package com.nixer.nprox.entity.swarm;

import java.io.Serializable;

/**
 * (SwarmNodes)实体类
 *
 * @author makejava
 * @since 2021-06-10 23:52:44
 */
public class SwarmNodes implements Serializable {
    private static final long serialVersionUID = -10253492225752328L;
    /**
     * 节点编号
     */
    private String id;

    private String code;
    /**
     * 矿机标识
     */
    private String server_code;
    /**
     * peerid
     */
    private String peer_id;
    /**
     * 类型
     */
    private String type;
    /**
     * 以太地址
     */
     private String address;
    /**
     * 节点ip
     */
    private String ip;
    /**
     * 区域
     */
    private String area;
    /**
     * 链接节点数
     */
    private Integer link_nodes;
    /**
     * 状态0离线  1在线
     */
    private Integer state;
    /**
     * 出票数
     */
    private Integer cheques;

    private Long bzz_get;

    private Long bzz_send;

    private Long bzz;

    private Integer loopnum;

    private Integer linkid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCheques() {
        return cheques;
    }

    public void setCheques(Integer cheques) {
        this.cheques = cheques;
    }



    public Integer getLoopnum() {
        return loopnum;
    }

    public void setLoopnum(Integer loopnum) {
        this.loopnum = loopnum;
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

    public Integer getLink_nodes() {
        return link_nodes;
    }

    public void setLink_nodes(Integer link_nodes) {
        this.link_nodes = link_nodes;
    }

    public Long getBzz_get() {
        return bzz_get;
    }

    public void setBzz_get(Long bzz_get) {
        this.bzz_get = bzz_get;
    }

    public Long getBzz_send() {
        return bzz_send;
    }

    public void setBzz_send(Long bzz_send) {
        this.bzz_send = bzz_send;
    }

    public Long getBzz() {
        return bzz;
    }

    public void setBzz(Long bzz) {
        this.bzz = bzz;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLinkid() {
        return linkid;
    }

    public void setLinkid(Integer linkid) {
        this.linkid = linkid;
    }
}
