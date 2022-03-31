package com.chengsy.code.tools.domain.bo;

/**
 * @author chengsiyi
 * @date 2020/4/23 19:23
 */
public class SevenMinuteFinaceInfoBO {


    /**
     * plateform : yingmi
     * code : 000577
     * name : 安信价值精选股票
     * category : STOCK
     * type : STOCK_FUND
     * cashDividend : true
     * total : 1998.72
     * available : 1998.72
     * totalAsset : 8544.53
     * totalProfit : 4724.37
     * netValue : 4.275
     * netValueDate : 1597939200000
     * profit : 49.97
     * profitDate : 1597939200000
     * profitRate : 0.0059
     * holdingProfit : 1490.3023
     * holdingProfitRate : 0.2113
     * unitValue : 3.5294
     */
    /**
     * 平台
     */
    private String plateform;
    /**
     * 代码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 类别
     */
    private String category;

    /**
     * 类型
     */
    private String type;

    /**
     * 现金分红
     */
    private boolean cashDividend;

    /**
     * 持有份额
     */
    private double total;

    /**
     * 可用份额
     */
    private double available;

    /**
     * 总金额
     */
    private double totalAsset;

    /**
     * 累计收益
     */
    private double totalProfit;

    /**
     * 最新净值
     */
    private double netValue;

    /**
     * 最新净值时间
     */
    private long netValueDate;

    /**
     * 最新收益
     */
    private double profit;

    /**
     * 收益时间
     */
    private long profitDate;

    /**
     * 日涨幅
     */
    private double profitRate;

    /**
     * 持仓收益
     */
    private double holdingProfit;

    /**
     * 持仓收益率
     */
    private double holdingProfitRate;

    /**
     * 持仓成本价
     */
    private double unitValue;

    public String getPlateform() {
        return plateform;
    }

    public void setPlateform(String plateform) {
        this.plateform = plateform;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCashDividend() {
        return cashDividend;
    }

    public void setCashDividend(boolean cashDividend) {
        this.cashDividend = cashDividend;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

    public double getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(double totalAsset) {
        this.totalAsset = totalAsset;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getNetValue() {
        return netValue;
    }

    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }

    public long getNetValueDate() {
        return netValueDate;
    }

    public void setNetValueDate(long netValueDate) {
        this.netValueDate = netValueDate;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public long getProfitDate() {
        return profitDate;
    }

    public void setProfitDate(long profitDate) {
        this.profitDate = profitDate;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getHoldingProfit() {
        return holdingProfit;
    }

    public void setHoldingProfit(double holdingProfit) {
        this.holdingProfit = holdingProfit;
    }

    public double getHoldingProfitRate() {
        return holdingProfitRate;
    }

    public void setHoldingProfitRate(double holdingProfitRate) {
        this.holdingProfitRate = holdingProfitRate;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

}
