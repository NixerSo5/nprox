package com.nixer.nprox.service.swarm;

import com.github.pagehelper.PageInfo;
import com.nixer.nprox.entity.SwarmTokenTotal;
import com.nixer.nprox.entity.SwarmTokens;
import com.nixer.nprox.entity.common.UserDetail;
import com.nixer.nprox.entity.common.dto.PageFindDto;
import com.nixer.nprox.entity.swarm.SwarmNodes;
import com.nixer.nprox.entity.swarm.dto.*;
import com.nixer.nprox.tools.ResultJson;

import java.util.List;

public interface SwarmService {
    SwarmDayDto getSwarmDay();

    UserPoolUnit userPoolState(long userid);

    UserNodesStateDto userNodesState(long userid);

    List<LineDateDto> userPoolStateLine(long userid);

    PageInfo<SwarmNodes> useNodesList(NodesFindDto nodesFindDto, long userid);

//    ResultJson useNodesAdd(UserNodeUpdateDto userNodeUpdateDto, long userid);
//
//    ResultJson useNodesDel(UserNodeUpdateDto userNodeUpdateDto, long userid);

    int getNodesNum();

    PageInfo<SwarmTokens> tokensList(PageFindDto pageFindDto, long userid);

    ResultJson<List<UserWalletDto>> userWallet(UserDetail userDetail);

    ResultJson activeWallet(long id, ActiveWalletDto activeWalletDto);

    SwarmTokenTotal tokensInfo(WalletInfoDto walletInfoDto);
}
