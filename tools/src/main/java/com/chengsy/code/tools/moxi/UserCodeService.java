package com.chengsy.code.tools.moxi;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chengsy.code.tools.moxi.mongo.ChannelUserCollection;
import com.chengsy.code.tools.moxi.mongo.UserCodeCollection;
import com.chengsy.code.tools.moxi.mongo.domain.ChannelUserEntity;
import com.chengsy.code.tools.moxi.mongo.domain.PoolEntity;
import com.chengsy.code.tools.moxi.mongo.domain.UserCodeEntity;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author chengsiyi
 * @date 2022/9/28 22:30
 */
@Slf4j
@Service
public class UserCodeService {

    @Resource
    private ChannelUserCollection channelUserCollection;

    @Resource
    private UserCodeCollection userCodeCollection;

    public void exportUser(Long appId) {
        EasyExcelUtils easyExcelUtils = new EasyExcelUtils();
        String absFilePath = "用户手机号.xlsx";
        easyExcelUtils.init(absFilePath, "Sheet1", Prize.class);
        List<String> emptyMobileUserId = new ArrayList<>();
        Long userCount = channelUserCollection.getUserCount(appId);
        AtomicInteger successCount = new AtomicInteger(0);
        long totalPage = userCount % 100 == 0 ? userCount / 100 : userCount / 100 + 1;
        for (long i = 0; i < totalPage; i++) {
            log.info("开始导出用户：totalCount:{},totalPage:{}, pageNo:{}", userCount, totalPage, i);
            List<ChannelUserEntity> userDetail = channelUserCollection.getUserDetail(10009L, i, 100);
            List<Prize> prizeList = new ArrayList<>();
            for (ChannelUserEntity s : userDetail) {
                Prize prize = new Prize();
                prize.setUserId(s.getUid());
                JSONObject detail = JSON.parseObject(s.getDetail());
                if (detail != null) {
                    prize.setPhone(detail.getString("phone"));
                } else if (s.getUserExt() != null) {
                    prize.setPhone(s.getUserExt().getString("phone"));
                } else {
                    emptyMobileUserId.add(s.getUid());
                }
                prizeList.add(prize);
            }
            easyExcelUtils.doExportExcel(prizeList);
            successCount.addAndGet(prizeList.size());
        }
        if (!emptyMobileUserId.isEmpty()) {
            log.info("用户无手机号:{}", JSON.toJSONString(emptyMobileUserId));
        }
        easyExcelUtils.finish();
        log.info("用户导出结束：成功条数:{}", successCount.get());
    }

    public Integer doExport(String activityId, Long startTime, Long endTime) {

        Map<String, String> userMap = new HashMap<>();

        List<Prize> prizeList = getPrizeList(activityId, startTime, endTime);
        List<String> openIds = prizeList.stream().map(Prize::getUserId).distinct().collect(Collectors.toList());

        List<String> emptyMobileUserId = new ArrayList<>();
        Lists.partition(openIds, 100).forEach(f -> {
            List<ChannelUserEntity> userDetail = channelUserCollection.getUserDetail(10009L, f);
            for (ChannelUserEntity s : userDetail) {
                JSONObject detail = JSON.parseObject(s.getDetail());
                if (detail != null) {
                    userMap.put(s.getUid(), detail.getString("phone"));
                } else if (s.getUserExt() != null) {
                    userMap.put(s.getUid(), s.getUserExt().getString("phone"));
                } else {
                    emptyMobileUserId.add(s.getUid());
                }
            }
        });

        if (!emptyMobileUserId.isEmpty()) {
            log.info("用户无手机号:{}", JSON.toJSONString(emptyMobileUserId));
        }


        for (Prize prize : prizeList) {
            String phone = userMap.get(prize.getUserId());
            prize.setPhone(phone);
        }

        Date date = new Date(startTime);
        String prizeFileName = DateUtil.format(date, "yyyy-MM-dd");
        String absFilePath = prizeFileName + "奖品.xlsx";
        EasyExcelUtils easyExcelUtils = new EasyExcelUtils();

        easyExcelUtils.init(absFilePath, "Sheet1", Prize.class);
        easyExcelUtils.doExportExcel(prizeList);
        easyExcelUtils.finish();
        return prizeList.size();
    }

    public List<Prize> getPrizeList(String activityId, Long startTime, Long endTime) {
        Double start = Double.valueOf(startTime);
        Double end = Double.valueOf(endTime);
        List<UserCodeEntity> userCode = userCodeCollection.getUserCode(activityId, start, end);

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
        return prizeList;
    }
}
