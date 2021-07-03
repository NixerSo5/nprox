package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel
public class AgentUserDto extends  AgentUserBase{

    @ApiModelProperty(value="userid")
    private String userid;

    @ApiModelProperty(value="创建时间")
    private String sctime;

    @ApiModelProperty(value="持有节点数")
    private Integer has_nodes_num;

    private Date ctime;

    @ApiModelProperty(value="用户账户")
    private String username;

    @ApiModelProperty(value="等级")
    private String level;

    public String getSctime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        if(this.getCtime()!=null){
            return sdf.format(this.getCtime());
        }
       return  sctime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setSctime(String sctime) {
        this.sctime = sctime;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getHas_nodes_num() {
        return has_nodes_num;
    }

    public void setHas_nodes_num(Integer has_nodes_num) {
        this.has_nodes_num = has_nodes_num;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
