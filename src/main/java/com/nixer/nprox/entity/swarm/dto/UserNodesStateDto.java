package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserNodesStateDto {

    @ApiModelProperty(value="用户在线节点数量")
    private String active_nodes_num;

    @ApiModelProperty(value="用户离线节点数量")
    private String offline_nodes_num;

    @ApiModelProperty(value="用户节点数量")
    private String user_total_num;

    @ApiModelProperty(value="矿池总节点数量")
    private String server_total_num;

    public String getActive_nodes_num() {
        return active_nodes_num;
    }

    public void setActive_nodes_num(String active_nodes_num) {
        this.active_nodes_num = active_nodes_num;
    }

    public String getOffline_nodes_num() {
        return offline_nodes_num;
    }

    public void setOffline_nodes_num(String offline_nodes_num) {
        this.offline_nodes_num = offline_nodes_num;
    }

    public String getUser_total_num() {
        return user_total_num;
    }

    public void setUser_total_num(String user_total_num) {
        this.user_total_num = user_total_num;
    }

    public String getServer_total_num() {
        return server_total_num;
    }

    public void setServer_total_num(String server_total_num) {
        this.server_total_num = server_total_num;
    }
}
