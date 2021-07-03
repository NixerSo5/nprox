package com.nixer.nprox.entity.common.dto;

import io.swagger.annotations.ApiModelProperty;

public class BindLoginDtoExt {

    @ApiModelProperty(value="绑定账号")
    private String bind;


    @ApiModelProperty(value="要绑定的验证码")
    private String bindcode;


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
}
