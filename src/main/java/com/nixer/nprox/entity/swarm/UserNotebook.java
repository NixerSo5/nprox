package com.nixer.nprox.entity.swarm;

import com.nixer.nprox.entity.swarm.dto.NoteBookDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserNotebook)实体类
 *
 * @author makejava
 * @since 2021-06-16 21:53:15
 */
public class UserNotebook extends NoteBookDto implements Serializable {
    private static final long serialVersionUID = 501675019800721736L;

    private Integer id;

    private Integer userid;

    private Date createDate;


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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
