package com.nixer.nprox.service.swarm.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nixer.nprox.dao.*;
import com.nixer.nprox.entity.*;
import com.nixer.nprox.entity.common.UserDetail;
import com.nixer.nprox.entity.common.dto.PageFindDto;
import com.nixer.nprox.entity.common.dto.SinglePramDto;
import com.nixer.nprox.entity.swarm.PoolNodes;
import com.nixer.nprox.entity.swarm.*;
import com.nixer.nprox.entity.swarm.dto.*;
import com.nixer.nprox.entity.swarm.pool.PoolConfig;
import com.nixer.nprox.service.swarm.SwarmService;
import com.nixer.nprox.tools.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SwarmServiceImpl implements SwarmService {

       @Autowired
       private RedisUtil redisUtil;

//        @Autowired
//        MongoTemplate mongoTemplate;

        @Autowired
        private SwarmDao swarmDao;

        @Autowired
        private SwarmTokensDao  swarmTokensDao;

        @Autowired
        private UserWalletDao userWalletDao;


        @Autowired
        private XchDayDao xchDayDao;

        @Autowired
        private XchUserDayDao xchUserDayDao;

        @Autowired
        private XchUserDao xchUserDao;

     public static final  BigDecimal GCD = new BigDecimal(0.0000000000000001);

    @Override
    public SwarmDayDto getSwarmDay() {
        List<SwarmDay> swarmDayList = swarmDao.getSwarmDayLastSevenDay();
        int daysize = swarmDayList.size();
        BigDecimal totalbzz = new BigDecimal(0);
        double totalcash_out = 0;
        if(daysize > 0){
            for(SwarmDay swarmDay:swarmDayList){
                totalcash_out += swarmDay.getCashout();
                totalbzz = totalbzz.add(new BigDecimal(swarmDay.getBzzout()).multiply(GCD));
            }

            String  poolConfigStr = redisUtil.get("POOL:STATE");
            PoolConfig poolConfig = JSONObject.parseObject(poolConfigStr,PoolConfig.class);

            //TODO
            poolConfig.setNode_num(String.valueOf(22));

            SwarmDayDto swarmDayDto =new SwarmDayDto();
            swarmDayDto.setBzz(poolConfig.getBzz());
            int nodenum = Integer.valueOf(poolConfig.getNode_num());
            swarmDayDto.setNode_num(poolConfig.getNode_num());
            swarmDayDto.setCashout_day(String.valueOf((int)Math.ceil(totalcash_out/daysize)));
            String bzzday = String.valueOf(totalbzz.divide(new BigDecimal(daysize),16, BigDecimal.ROUND_HALF_UP));
            if(bzzday.equals("0E-16")){
                bzzday="0";
            }
            swarmDayDto.setBzz_day(bzzday);
            String singlenodecashout =
                    String.valueOf(new BigDecimal(totalcash_out/nodenum).divide(new BigDecimal(daysize),16, BigDecimal.ROUND_HALF_UP));
            if(singlenodecashout.equals("0E-16")){
                singlenodecashout="0";
            }
            swarmDayDto.setSingle_node_cashout(singlenodecashout);
            String singlenodeprofit = String.valueOf(totalbzz.divide(new BigDecimal(nodenum),16,
                    BigDecimal.ROUND_HALF_UP));
            if(singlenodeprofit.equals("0E-16")){
                singlenodeprofit="0";
            }
            swarmDayDto.setSingle_node_profit(singlenodeprofit);
            return swarmDayDto;
        }
        return null;
    }

    @Override
    public BzzUserPoolUnit userPoolState(long userid) {
        BzzUserPoolUnit userPoolUnit = new BzzUserPoolUnit();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        Date start = c.getTime();
        String beforday= sdf.format(start);//前一天
        //用户总计
        SwarmUserTotalExt swarmUserTotal = swarmDao.getSwarmUserTotal(userid);
        SwarmUserTotalDto swarmUserTotalDto = new SwarmUserTotalDto();
        swarmUserTotalDto.setTotal_cashout(swarmUserTotal.getTotal_cashout());
        swarmUserTotalDto.setTotal_get_bzz(new BigDecimal(swarmUserTotal.getTotal_get_bzz()).multiply(GCD));
        swarmUserTotalDto.setTotal_send_bzz(new BigDecimal(swarmUserTotal.getTotal_send_bzz()).multiply(GCD));
        swarmUserTotalDto.setBzz(new BigDecimal(swarmUserTotal.getBzz()).multiply(GCD));
        swarmUserTotalDto.setTotal_node_num(swarmUserTotal.getTotal_node_num());
        userPoolUnit.setSwarmUserTotal(swarmUserTotalDto);
        String nodesnum = redisUtil.get("POOL:NODES");
        SwarmUserDayExt  swarmUserDay = swarmDao.getSwarmUserDay(userid,sdf.format(new Date()));
        SwarmUserDayDto swarmUserDayDto=new SwarmUserDayDto();
        //今日没有数据添加昨天数据来做
        if(swarmUserDay==null){
            SwarmUserDayExt  oldswarmUserDay = swarmDao.getSwarmUserDay(userid,beforday);
            swarmUserDayDto.setNode_num(oldswarmUserDay.getNode_num());
            swarmUserDayDto.setBzz(new BigDecimal(0));
            swarmUserDayDto.setCash_out(0);
            swarmUserDayDto.setDate(sdf.format(new Date()));
            swarmUserDayDto.setUtime(new Date());
        }else{
            swarmUserDayDto.setNode_num(swarmUserDay.getNode_num());
            swarmUserDayDto.setBzz(new BigDecimal(swarmUserDay.getBzz()).multiply(GCD));
            swarmUserDayDto.setCash_out(swarmUserDay.getCash_out());
            swarmUserDayDto.setDate(swarmUserDay.getDate());
            swarmUserDayDto.setUtime(swarmUserDay.getUtime());
        }
        userPoolUnit.setSwarmUserDay(swarmUserDayDto);
        SwarmDay swarmDay = swarmDao.getSwarmDay(sdf.format(new Date()));
        if(swarmDay==null){
            swarmDay =new SwarmDay();
            swarmDay.setBzzout(0L);
            swarmDay.setCashout(0);
            swarmDay.setNodes_num(Integer.valueOf(nodesnum));
            swarmDay.setUpdate_date(sdf.format(new Date()));
        }
        UserNodesStateDto userNodesStateDto = this.userNodesState(userid);
        userPoolUnit.setUserNodesStateDto(userNodesStateDto);
        BigDecimal userNodeNum = new BigDecimal(userNodesStateDto.getUser_total_num());
        //计算值
        SwarmUserExp swarmUserExp = new SwarmUserExp();
        swarmUserExp.setUser_day_bzz(new BigDecimal(swarmDay.getBzzout()).multiply(GCD).divide(new BigDecimal(swarmDay.getNodes_num()),16,
                BigDecimal.ROUND_HALF_UP).multiply(userNodeNum));
        swarmUserExp.setUser_day_cashout(new BigDecimal(swarmDay.getCashout()).divide(new BigDecimal(swarmDay.getNodes_num()),16,BigDecimal.ROUND_HALF_UP).multiply(userNodeNum));
        userPoolUnit.setSwarmUserExp(swarmUserExp);
        return userPoolUnit;
    }

    @Override
    public UserNodesStateDto userNodesState(long userid) {
        List<PoolNodes> poolNodesList = swarmDao.getUserNodesState(userid);
        UserNodesStateDto userNodesStateDto = new UserNodesStateDto();
        String  swar = redisUtil.get("POOL:STATE");
        PoolConfig poolConfig = JSONObject.parseObject(swar,PoolConfig.class);
        userNodesStateDto.setServer_total_num(poolConfig.getNode_num());
        int userTotal = 0;
        int active = 0;
        int offline = 0;
        for(PoolNodes poolNodes:poolNodesList){
           if(poolNodes.getState() == 0){
               offline+=poolNodes.getNum();
           }else {
               active+=poolNodes.getNum();
           }
        }
        userTotal = active + offline;
        userNodesStateDto.setOffline_nodes_num(String.valueOf(offline));
        userNodesStateDto.setActive_nodes_num(String.valueOf(active));
        userNodesStateDto.setUser_total_num(String.valueOf(userTotal));
        return userNodesStateDto;
    }

    @Override
    public List<LineDateDto> userBzzPoolStateLine(long userid) {
        List<UserSwarmLine> userSwarmLines = swarmDao.userPoolStateLine(userid);
        List<LineDateDto> lineDateDtoList = new ArrayList<>();
        //TODO 0没有做处理 ?? userbzz 没用
        for(UserSwarmLine userSwarmLine:userSwarmLines){
            System.out.println(userSwarmLine.getDate());
            BigDecimal single_node_cashout = new BigDecimal(0);
            BigDecimal single_node_bzz = new BigDecimal(0);
            if(userSwarmLine.getPool_nodes_num()!=0){
                single_node_cashout =
                        new BigDecimal(userSwarmLine.getPool_cashout()).divide(new BigDecimal(userSwarmLine.getPool_nodes_num()),16, BigDecimal.ROUND_HALF_UP);
                single_node_bzz = new BigDecimal(userSwarmLine.getPool_bzz()).multiply(GCD)
                        .divide(new BigDecimal(userSwarmLine.getPool_nodes_num()),16,
                                BigDecimal.ROUND_HALF_UP);
            }
            LineDateDto lineDateDto = new LineDateDto();
            lineDateDto.setDate(userSwarmLine.getDate());
            lineDateDto.setCashout(String.valueOf(single_node_cashout.multiply(new BigDecimal(userSwarmLine.getUser_nodes_num())).toPlainString()));
            lineDateDto.setCheques(String.valueOf(single_node_bzz.multiply(new BigDecimal(userSwarmLine.getUser_nodes_num())).toPlainString()));
            lineDateDtoList.add(lineDateDto);
        }
        return lineDateDtoList;
    }

    @Override
    public PageInfo<SwarmNodes> useNodesList(NodesFindDto nodesFindDto, long userid) {
        PageHelper.startPage(nodesFindDto.getIndex(), nodesFindDto.getSize());
        List<SwarmNodes> lists = swarmDao.useNodesList(userid,nodesFindDto.getState());
        PageInfo<SwarmNodes> pageInfo = new PageInfo<SwarmNodes>(lists);
        return pageInfo;
    }

    @Override
    public ResultJson<List<UserWalletDto>> userWallet(UserDetail userDetail) {
        long userid = userDetail.getId();
        List<UserWalletDto> userWalletDtoList = userWalletDao.getWalletListByUserId(userid);
        return  ResultJson.ok(userWalletDtoList);
    }

    @Override
    public ResultJson activeWallet(long userid, ActiveWalletDto activeWalletDto) {
        //TODO 具体业务没做
        UserWallet userWallet = userWalletDao.findByUserAndTokenId(userid,activeWalletDto.getTokenid());
        if(userWallet !=null ){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"已存在该钱包");
        }
        userWallet = new UserWallet();
        userWallet.setBalance(new BigInteger("0"));
        userWallet.setUserid((int) userid);
        userWallet.setTokenid(activeWalletDto.getTokenid());
        userWallet.setCashout(new BigInteger("0"));
        userWallet.setUnitnum(0l);
        userWalletDao.insert(userWallet);
        if(userWallet.getId()>0){
            return ResultJson.ok();
        }
        return ResultJson.failure(ResultCode.BAD_REQUEST,"保存失败");
    }

    @Override
    public SwarmTokenTotal tokensInfo(WalletInfoDto walletInfoDto) {
        SwarmTokenTotal swarmTokensTotal = swarmDao.tokensInfo(walletInfoDto);
        if(swarmTokensTotal ==null){
            swarmTokensTotal =  new SwarmTokenTotal();
            swarmTokensTotal.setTotal(0l);
            swarmTokensTotal.setGcd(0);
            swarmTokensTotal.setToday(0l);
            swarmTokensTotal.setYesterday(0l);
            swarmTokensTotal.setUtime(new Date());
        }
        return swarmTokensTotal;
    }

    @Override
    public UserTokenPoolDto userTokenPreview(long userid, SinglePramDto singlePramDto) {
        UserTokenPoolDto userTokenPoolDto = new UserTokenPoolDto();
        SwarmTokens swarmTokens = swarmTokensDao.queryById(Integer.valueOf(singlePramDto.getDoid()));
        switch (swarmTokens.getTokenname()){
            case "BZZ":
                BzzUserPoolUnit bzzUserPoolUnit = this.userPoolState(userid);
                userTokenPoolDto.setBzzUserPool(bzzUserPoolUnit);
                break;
            case "XCH":
                XchUserPool xchUserPool = this.xchUserPoolState(userid,swarmTokens);
                userTokenPoolDto.setXchUserPool(xchUserPool);
                break;
        }
         return userTokenPoolDto;
    }

    @Override
    public UserTokenLineDto userTokenPoolStateLine(long userid, SinglePramDto singlePramDto) {
        UserTokenLineDto userTokenLineDto = new UserTokenLineDto();
        SwarmTokens swarmTokens = swarmTokensDao.queryById(Integer.valueOf(singlePramDto.getDoid()));
        switch (swarmTokens.getTokenname()){
            case "BZZ":
                List<LineDateDto> lineDateDtoList = this.userBzzPoolStateLine(userid);
                userTokenLineDto.setBzzLine(lineDateDtoList);
                break;
            case "XCH":
                List<XchLineDataDto> xchUserPool = this.userXchPoolStateLine(userid,swarmTokens);
                userTokenLineDto.setXchLine(xchUserPool);
                break;
        }
        return userTokenLineDto;
    }

    private List<XchLineDataDto> userXchPoolStateLine(long userid, SwarmTokens swarmTokens) {
        List<XchLineDataDto> lineDataDtos =  new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        Date dend = c.getTime();
        String end = sdf.format(dend);//前一天
        c.add(Calendar.DATE, -29);
        Date dstart = c.getTime();
        String start = sdf.format(dstart);
        System.out.println(start+end);
        List<XchUserDay> xchUserPool = xchUserDayDao.getUserXchDayPoolLine(userid,start,end);
        if(xchUserPool.size()<30){
            Calendar d = Calendar.getInstance();
            d.setTime(new Date());
            d.add(Calendar.DATE, -31);
            for (int i = 1; i < 31; i++) {
                d.add(Calendar.DATE, +1);
                Date fx = d.getTime();
                String nowdata = sdf .format(fx);
                System.out.println(nowdata);
                BigDecimal cashout = new BigDecimal(0);
                for(XchUserDay xchUserDay:xchUserPool){
                    if(xchUserDay.getDate().equals(nowdata)){
                        cashout = new BigDecimal(xchUserDay.getCashout()).divide(new BigDecimal(Math.pow(10,
                                swarmTokens.getGcd())),swarmTokens.getGcd(),BigDecimal.ROUND_HALF_UP);
                    }
                }
                XchLineDataDto xchLineDataDto = new XchLineDataDto();
                xchLineDataDto.setDate(nowdata);
                xchLineDataDto.setCashout(cashout.toPlainString());
                lineDataDtos.add(xchLineDataDto);
            }
        }
        return lineDataDtos;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        Date start = c.getTime();
        String beforday = sdf.format(start);//前一天

        c.add(Calendar.DATE, -29);
        Date startx = c.getTime();
        String beforday30 = sdf.format(startx);

        System.out.println(beforday30+"/"+beforday);
        Calendar d = Calendar.getInstance();
        d.setTime(new Date());
        d.add(Calendar.DATE, -30);
        Date fx = d.getTime();
        System.out.println(sdf.format(fx));
        for (int i = 1; i < 30; i++) {
            d.add(Calendar.DATE, +1);
             fx = d.getTime();
            System.out.println(sdf.format(fx));
        }
    }

    private XchUserPool xchUserPoolState(long userid,SwarmTokens swarmTokens) {
        XchUserPool xchUserPool = new XchUserPool();
        //总收益 //农田总量  //农田大小 //昨日产出  //昨日本地产出
        UserWallet userWallet = userWalletDao.getWalletByUserIdAndTokenId(userid,String.valueOf(swarmTokens.getId()));
        if(userWallet==null){
            return new XchUserPool();
        }
        XchUser xchUser = xchUserDao.findByUserId(userid);
        if(xchUser==null){
            xchUser =new XchUser();
            xchUser.setFramjson("[]");
            xchUser.setFramnum(0l);
            xchUser.setFramsize(0l);
        }
        BigDecimal usertotal =
                new BigDecimal(userWallet.getCashout().add(
                        userWallet.getBalance())).divide(new BigDecimal(Math.pow(10,swarmTokens.getGcd())),
                        swarmTokens.getGcd(),BigDecimal.ROUND_HALF_UP);
        xchUserPool.setTotalcash(usertotal.toPlainString());
        xchUserPool.setFramsize(StringUtils.longToString(userWallet.getUnitnum()));
        xchUserPool.setFramnum(StringUtils.longToString(xchUser.getFramnum()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        Date start = c.getTime();
        String beforday= sdf.format(start);//前一天
        XchUserDay xchUserDay = xchUserDayDao.getUserXchDay(userid,beforday);
        if(xchUserDay==null){
            xchUserDay = new XchUserDay();
            xchUserDay.setCashout(new BigInteger("0"));
        }
        xchUserPool.setYesterdaycash(new BigDecimal(xchUserDay.getCashout()).divide(new BigDecimal(Math.pow(10,
                swarmTokens.getGcd())),
                swarmTokens.getGcd(),BigDecimal.ROUND_HALF_UP).toPlainString());
        xchUserPool.setYesterdaylocal("0");//TODO
        return xchUserPool;
    }

//    @Override
//    public ResultJson useNodesAdd(UserNodeUpdateDto userNodeUpdateDto, long userid) {
//        //查询是否存在该节点该节点是否存在关系h
//        int num = swarmDao.findNodeByAddress(userNodeUpdateDto.getNode_uid());
//        if(num<=0){
//            return ResultJson.failure("节点不存在");
//        }else{
//            SwarmUserNode swarmUserNode = swarmDao.findNodeByAddressCanUse(userNodeUpdateDto.getNode_uid());
//            if(swarmUserNode!=null){
//                return ResultJson.failure("节点被锁定");
//            }else{
//                swarmUserNode =new SwarmUserNode();
//                swarmUserNode.setNode_uid(userNodeUpdateDto.getNode_uid());
//                swarmUserNode.setUserid((int) userid);
//                swarmDao.saveUserNode(swarmUserNode);
//                if(swarmUserNode.getId()>0){
//                    return ResultJson.ok();
//                }else{
//                    return  ResultJson.failure("保存失败");
//                }
//            }
//        }
//    }

//    @Override
//    public ResultJson useNodesDel(UserNodeUpdateDto userNodeUpdateDto, long userid) {
//        int i = swarmDao.delUserNode(userid,userNodeUpdateDto.getNode_uid());
//        return ResultJson.ok();
//    }

    @Override
    public int getNodesNum() {
        return this.swarmDao.getNodesNum();
    }

    @Override
    public PageInfo<SwarmTokens> tokensList(PageFindDto pageFindDto, long userid) {
        PageHelper.startPage(pageFindDto.getIndex(), pageFindDto.getSize());
        List<SwarmTokens> lists = swarmTokensDao.tokensList(userid,null);
        PageInfo<SwarmTokens> pageInfo = new PageInfo<SwarmTokens>(lists);
        return pageInfo;
    }


    public static void main2(String[] args) {
//        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
//        for (int i = 30; i >= 0; i--) {
//            Calendar c = Calendar.getInstance();
//            c.setTime(new Date());
//            c.add(Calendar.DATE, -i);
//            Date start = c.getTime();
//            String beforday= sdf.format(start);//前一天
//            System.out.println(beforday);
//        }
        int cq = 3600;
        long x = 2000000000000l;
        Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            int stq=0;
           int s = rand.nextInt(360);
            int sf = rand.nextInt(2);
            //7718181175890047
          //  long xfs = rand.nextLong(7718181175890047l);
            if(sf ==0){
                stq= cq+s;
            }else{
                stq =cq-s;
            }
            long cqcs = x*stq;
           System.out.println(stq+"==="+cqcs);
        }
    }
//      mongoTemplate.save(info);
//      return mongoTemplate.findOne(query, UserLoginInfo.class);
//      mongoTemplate.updateMulti(query, update, UserLoginInfo.class);
//      return mongoTemplate.findAndRemove(query, UserLoginInfo.class);


    public static void mainx(String[] args) throws ParseException, IOException {
        String jsonstr = HttpUtil.doGet("https://api2.chiaexplorer.com/chart/xchTibDay?period=2w");
        JSONObject jso =   JSONObject.parseObject(jsonstr);
        JSONArray jsa = jso.getJSONArray("timestamp");
        JSONArray jsadata = jso.getJSONArray("data");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time =  sdf.format(new Date());
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(time));
        c.add(Calendar.DATE, -1);
        long endtime = sdf.parse(time).getTime();
        long starttime = c.getTime().getTime();
        BigDecimal rvalue = new BigDecimal(0);
        int numbers = 0;
        for (int i = 0; i < jsa.size(); i++) {
            long ntime = jsa.getLong(i);
            if(ntime > starttime && ntime < endtime){
                rvalue =  rvalue.add(new BigDecimal(jsadata.getString(i)));
                numbers++;
            }
        }
        rvalue = rvalue.multiply(new BigDecimal(384)).divide(new BigDecimal(numbers),10,BigDecimal.ROUND_HALF_UP);
        rvalue = rvalue.add(rvalue.multiply(new BigDecimal(0.1)));
        System.out.println(rvalue);

    }

    public static void main4(String[] args) throws Exception{
        HttpClientBuilder builder = HttpClients.custom();
        //对照UA字串的标准格式理解一下每部分的意思
        builder.setUserAgent("Mozilla/5.0(Windows;U;Windows NT 5.1;en-US;rv:0.9.4)");
        final CloseableHttpClient httpClient = builder.build();
        // 1. 生成Http对象
        HttpGet httpGet = new HttpGet("https://api2.chiaexplorer.com/chart/xchTibDay?period=2w");
        // 2.1 设置http请求头 User-Agent ,模拟第一次请求,
        httpGet.setHeader("User-Agent","Mozilla/5.0(Windows NT 6.1;Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        // 3. 执行httpGet
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 4. 获取返回的实体
        HttpEntity httpEntity = response.getEntity();
        // 5. 解析实体类
        String entityJson = EntityUtils.toString(httpEntity,"utf-8");
        // 6. 打印数据,观看结果
        System.out.println("返回的数据是:" + entityJson);
        // 7. 关闭连接对象
        response.close();
        httpClient.close();
    }


}
