package com.chengsy.code.tools.moxi;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengsiyi
 * @date 2022/9/28 22:57
 */
public class PrizeListener extends AnalysisEventListener<Prize> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrizeListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Prize> list = new ArrayList<>();
    EasyExcelUtils utils;

    public PrizeListener() {
        List<String> lsCol = new ArrayList<>(1);
        lsCol.add("用户id");
        lsCol.add("奖品名称");
        lsCol.add("手机号");
        lsCol.add("获奖时间");

        utils = new EasyExcelUtils();
        utils.init("最新的奖品.xlsx", "Sheet1", lsCol);
    }

    @Override
    public void invoke(Prize data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            fillExcel(list);
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        LOGGER.info("所有数据解析完成！");
        utils.finish();
    }


    private void fillExcel(List<Prize> prize) {
        utils.doExportExcel(prize);
    }
}
