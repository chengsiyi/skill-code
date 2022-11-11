package com.chengsy.code;

import com.chengsy.code.tools.moxi.Prize;
import com.chengsy.code.tools.moxi.UserCodeService;
import com.chengsy.code.tools.moxi.mongo.ChannelUserCollection;
import com.chengsy.code.tools.moxi.mongo.UserCodeCollection;
import com.chengsy.code.tools.moxi.mongo.domain.ChannelUserEntity;
import com.chengsy.code.tools.moxi.mongo.domain.PoolEntity;
import com.chengsy.code.tools.moxi.mongo.domain.UserCodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chengsiyi
 * @date 2022/10/2 16:03
 */

@Slf4j
public class ExportPrizeTest extends BaseTest {

    @Resource
    private ChannelUserCollection userCollection;
    @Resource
    private UserCodeCollection userCodeCollection;
    @Resource
    private UserCodeService user;

    @Test
    public void getUserDetail() {
        List<String> uid = new ArrayList<>();
        uid.add("ohHKOjg-CYFakPCabaWtTegCWDvI");
        List<ChannelUserEntity> userDetail = userCollection.getUserDetail(10009L, null);
        System.out.println(userDetail);
    }

    @Test
    public void export() throws Exception {

        String activityId = "63563cae25c677000175272a";

        DateUtils.ceiling(new Date(),Calendar.HOUR_OF_DAY);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Long startTime = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Long endTime = calendar.getTimeInMillis();

        log.info("开始导出数据:{}-{}", startTime, endTime);
        user.doExport(activityId, startTime, endTime);
    }

    @Test
    public void exportUserPhone(){
        user.exportUser(10009L);
    }

    @Test
    public void usercode() throws Exception {
        String activityId = "632d1c7874420100010e6817";
        List<UserCodeEntity> userCode = userCodeCollection.getUserCode(activityId, null, null);

        List<String> poolIds = userCode.stream().map(UserCodeEntity::getSPoolId).collect(Collectors.toList());
        List<PoolEntity> poolList = userCodeCollection.getPoolList(activityId, poolIds);

        Map<String, String> poolNameMap = poolList.stream().collect(Collectors.toMap(PoolEntity::getId, PoolEntity::getSTitle, (o, n) -> n));

        List<Prize> prizeList = new ArrayList<>();
        for (UserCodeEntity codeEntity : userCode) {

            Prize prize = new Prize();
            prize.setUserId(codeEntity.getSUserId());
            prize.setPrizeName(poolNameMap.get(codeEntity.getSPoolId()));
            prize.setGetTime(DateFormatUtils.format(codeEntity.getICreateTime().longValue(), "yyyy-MM-dd HH:mm:ss"));
            prizeList.add(prize);
        }


        System.out.println(prizeList);
    }
}
