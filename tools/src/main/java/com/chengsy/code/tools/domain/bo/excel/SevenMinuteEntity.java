package com.chengsy.code.tools.domain.bo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;

/**
 * @author chengsiyi
 * @date 2020/9/12 19:33
 */
public class SevenMinuteEntity {
    @ExcelProperty("平台")
    private String plateform;
    /**
     * 代码
     */
    @ExcelProperty("基金代码")
    private String code;
    /**
     * 名称
     */
    @ExcelProperty("基金名称")
    private String name;
    /**
     * 类别
     */
    @ExcelProperty("基金类别")
    private String category;

    /**
     * 类型
     */
    @ExcelProperty("基金类型")
    private String type;

    /**
     * 持有份额
     */
    @ExcelProperty("持有份额")
    private double total;

    /**
     * 可用份额
     */
    @ExcelProperty("可用份额")
    private double available;

    /**
     * 总金额
     */
    @ExcelProperty("总金额")
    private double totalAsset;

    /**
     * 累计收益
     */
    @ExcelProperty("累计收益")
    private double totalProfit;

    /**
     * 最新净值
     */
    @ExcelProperty("最新净值")
    private double netValue;

    /**
     * 最新净值时间
     */
    @DateTimeFormat("最新净值时间")
    private long netValueDate;

    /**
     * 最新收益
     */
    @ExcelProperty("最新收益")
    private double profit;

    /**
     * 收益时间
     */
    @ExcelProperty("持有份额")
    private long profitDate;

    /**
     * 日涨幅
     */
    @ExcelProperty("持有份额")
    private double profitRate;

    /**
     * 持仓收益
     */
    @ExcelProperty("持有份额")
    private double holdingProfit;

    /**
     * 持仓收益率
     */
    @ExcelProperty("持有份额")
    private double holdingProfitRate;

    /**
     * 持仓成本价
     */
    @ExcelProperty("持有份额")
    private double unitValue;
}
