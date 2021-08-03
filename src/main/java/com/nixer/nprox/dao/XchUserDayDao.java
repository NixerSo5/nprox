package com.nixer.nprox.dao;

import com.nixer.nprox.entity.XchDay;
import com.nixer.nprox.entity.XchUserDay;
import com.nixer.nprox.entity.swarm.dto.XchLineDataDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (XchUserDay)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-30 15:40:28
 */
@Repository
public interface XchUserDayDao {

    XchUserDay getUserXchDay(@Param("userid")long userid, @Param("beforday")String beforday);

    List<XchUserDay> getUserXchDayPoolLine(@Param("userid")long userid,@Param("start") String start,@Param("end") String end);
}

