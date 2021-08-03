package com.nixer.nprox.entity.swarm.dto;

import java.util.List;

public class UserTokenLineDto {

    private List<LineDateDto> bzzLine;

    private List <XchLineDataDto> xchLine;


    public List<LineDateDto> getBzzLine() {
        return bzzLine;
    }

    public void setBzzLine(List<LineDateDto> bzzLine) {
        this.bzzLine = bzzLine;
    }

    public List<XchLineDataDto> getXchLine() {
        return xchLine;
    }

    public void setXchLine(List<XchLineDataDto> xchLine) {
        this.xchLine = xchLine;
    }
}
