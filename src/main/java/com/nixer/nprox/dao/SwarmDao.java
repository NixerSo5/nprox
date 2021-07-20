package com.nixer.nprox.dao;


import com.nixer.nprox.entity.SwarmTokenTotal;
import com.nixer.nprox.entity.common.SysConfig;
import com.nixer.nprox.entity.swarm.*;
import com.nixer.nprox.entity.swarm.dto.WalletInfoDto;
import com.nixer.nprox.entity.swarm.pool.CashOutConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwarmDao {

    List<SwarmDay> getSwarmDayLastSevenDay();

    SwarmUserTotalExt getSwarmUserTotal(long userid);

    SwarmUserDayExt getSwarmUserDay(@Param("userid") long userid,@Param("date") String date);

    List<PoolNodes> getUserNodesState(long userid);

    SwarmDay getSwarmDay(String date);

    List<UserSwarmLine> userPoolStateLine(long userid);

    List<SwarmNodes> useNodesList(@Param("userid")long userid, @Param("state") Integer state);

    int findNodeByAddress(String id);

//    SwarmUserNode findNodeByAddressCanUse(String uid);
//
//    void saveUserNode(SwarmUserNode swarmUserNode);

    int delUserNode(@Param("userid")long userid,@Param("node_uid") String node_uid);

    int getNodesNum();

    SysConfig getSysConfigOutCash();

    SwarmTokenTotal tokensInfo(WalletInfoDto walletInfoDto);
}
