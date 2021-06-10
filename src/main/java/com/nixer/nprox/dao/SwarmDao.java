package com.nixer.nprox.dao;


import com.nixer.nprox.entity.swarm.*;
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

    int findNodeByAddress(String address);

    SwarmUserNode findNodeByAddressCanUse(String address);

    void saveUserNode(SwarmUserNode swarmUserNode);

    int delUserNode(@Param("userid")long userid,@Param("node_address") String node_address);

    int getNodesNum();
}
