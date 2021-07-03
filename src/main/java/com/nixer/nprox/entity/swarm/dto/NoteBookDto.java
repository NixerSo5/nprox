package com.nixer.nprox.entity.swarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.dsig.SignatureMethod;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel
public class NoteBookDto {


    @ApiModelProperty(value="地址")
    private String address;//: '地址',

    @ApiModelProperty(value="备注")
    private String remarks;//: '备注',

    @ApiModelProperty(value ="创建时间")
    private String ctime;//: '备注',

    @ApiModelProperty(value ="创建时间")
    private Date create_date;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCtime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        if(this.getCreate_date()!=null){
            return simpleDateFormat.format(this.getCreate_date());
        }
        return  ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
