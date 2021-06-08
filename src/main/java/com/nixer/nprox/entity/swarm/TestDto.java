package com.nixer.nprox.entity.swarm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "collection")//此注解对应mongodb集合
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDto implements Serializable {

    private static final long serialVersionUID = -8985545025018238754L;
    private String id;
    private int userId;
    private String username;
    @CreatedDate
    private Date loginDate;
}
