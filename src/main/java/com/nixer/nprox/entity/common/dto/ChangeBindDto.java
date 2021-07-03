package com.nixer.nprox.entity.common.dto;

import io.swagger.annotations.ApiModelProperty;

public class ChangeBindDto {


    @ApiModelProperty(value="修改绑定账号")
    private String bind;

    @ApiModelProperty(value="修改绑定账号验证码")
    private String bindcode;


    @ApiModelProperty(value="绑定类型  0手机 1邮箱")
    private Integer bindtype;


    public String getBind() {
        return bind;
    }

    public void setBind(String bind) {
        this.bind = bind;
    }

    public String getBindcode() {
        return bindcode;
    }

    public void setBindcode(String bindcode) {
        this.bindcode = bindcode;
    }

    public Integer getBindtype() {
        return bindtype;
    }

    public void setBindtype(Integer bindtype) {
        this.bindtype = bindtype;
    }
}
