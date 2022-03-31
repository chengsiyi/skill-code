package com.chengsy.code.tools.domain.bo;

import java.util.List;

/**
 * @author chengsiyi
 * @date 2020/7/9 20:04
 */
public class ResultListBean {
    /**
     * id : 2600188825114310
     * itemCode : 109415
     * itemName : 中银医疗保健混合
     * lastDayIncome : null
     * totalIncome : -1.19
     * keepAmount : 406.5

     * type : 1
     * redemption : 0
     * ready : true
     * applyCount : 2
     * redemptionID : null
     * freshDate : 1594137600000
     * pureAmount : 2.4571
     * awaitDays : null
     * specId : 203
     * applyAmount : 998.81
     * applyDate : 1593588700000
     * modified : 1594196039000
     * redemptionDate : null
     * redemptionAmount : null
     * redemptionTarget : null
     * redemptionBank : null
     * redemptionCardNo : null
     * redemptionBankName : null
     * deadline : null
     * expireTips : null
     * insuranceNo : null
     * redemptionInfoList : []
     * tranBuyDetailSonList : []
     * expectIncomeAmount : 0.0
     * order_type : 0
     * cancelFlag : 1
     * ope_type : 1
     * navDate : 2020-07-08 00:00:00
     * fundRevenuePerMillion : null
     * expectLastIncome : null
     * latestIncome : 2.93
     * s_merchant : 110200234001
     * calcInterestTimeStr : null
     * insuranceType : null
     * itemSpecCode : 005689
     * redempting : null
     * redemptAmount : null
     * salesChannel : 11
     * urlTransfer : //trade.jr.jd.com/fund/toChange.action?item_id=109415&distinctCode=2
     * yyRedemptionFlag : null
     * nofeetimeDataType : null
     * curMonthRedempted : null
     * insTransfer : null
     * redemptionPayInfo : null
     * dividentStr : null
     * distinctCode : 2
     * extInfoList : [{"extKey":"needIncomeDate","extValue":"2020-07-08"},{"extKey":"needSpecAmount","extValue":"2.4571"},{"extKey":"needAmountKeep","extValue":"406.5000"},{"extKey":"optionalIncome","extValue":"2.9300"},{"extKey":"incomeUpdateFlag","extValue":"0"},{"extKey":"needExpectIncome","extValue":"2"},{"extKey":"redemption","extValue":"1"},{"extKey":"nav","extValue":"1"},{"extKey":"pureAmount","extValue":"2.4571"},{"extKey":"fundPriceRate","extValue":"0.2939"},{"extKey":"convert","extValue":"1"},{"extKey":"fund_purchasetype","extValue":"1"},{"extKey":"fund_feeway","extValue":"100"},{"extKey":"fundShortName","extValue":"中银医疗保健混合"},{"extKey":"catName","extValue":"混合型"},{"extKey":"fundPriceRate","extValue":"0.2939"},{"extKey":"priceRateed","extValue":"0.2939"},{"extKey":"fundPurchasetype","extValue":"1"}]
     * ref_fund_purchasetype : 3
     * maxCreated : 1594087666000
     * systemSerial : fdd2020063093170013651
     * todayIncome : null
     * confirmShare : 406.5
     * confirmNetValue : 2.4627
     * confirmAmount : null
     * redemptionDateStr : null
     * applyDateString : 2020-07-01
     * freshDateString : 2020年07月08日
     * redemptionD : null
     * navDateString : 07月08日
     */

    private long id;
    private String itemCode;
    private String itemName;
    private Object lastDayIncome;
    private double totalIncome;
    private double keepAmount;
    private int type;
    private String redemption;
    private boolean ready;
    private int applyCount;
    private Object redemptionID;
    private long freshDate;
    private int status;
    private double pureAmount;
    private Object awaitDays;
    private double showRate;
    private String specId;
    private double applyAmount;
    private Object remark;
    private Object remarkB;
    private long applyDate;
    private long modified;
    private Object redemptionDate;
    private Object redemptionAmount;
    private Object redemptionTarget;
    private Object redemptionBank;
    private Object redemptionCardNo;
    private Object redemptionBankName;
    private Object deadline;
    private Object expireTips;
    private Object insuranceNo;
    private double expectIncomeAmount;
    private int order_type;
    private String cancelFlag;
    private int ope_type;
    private String navDate;
    private Object fundRevenuePerMillion;
    private Object expectLastIncome;
    private double latestIncome;
    private String s_merchant;
    private Object calcInterestTimeStr;
    private Object extA;
    private Object extB;
    private Object extC;
    private Object insuranceType;
    private String itemSpecCode;
    private Object redempting;
    private Object redemptAmount;
    private int salesChannel;
    private String urlTransfer;
    private Object yyRedemptionFlag;
    private Object nofeetimeDataType;
    private Object curMonthRedempted;
    private Object insTransfer;
    private Object redemptionPayInfo;
    private Object dividentStr;
    private int distinctCode;
    private int ref_fund_purchasetype;
    private long maxCreated;
    private String systemSerial;
    private Object todayIncome;
    private double confirmShare;
    private double confirmNetValue;
    private Object confirmAmount;
    private Object redemptionDateStr;
    private String applyDateString;
    private String freshDateString;
    private String shortDateString;
    private Object redemptionD;
    private String navDateString;
    private List<?> redemptionInfoList;
    private List<?> tranBuyDetailSonList;
    private List<OperateListBean> operateList;
    private List<ExtInfoListBean> extInfoList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Object getLastDayIncome() {
        return lastDayIncome;
    }

    public void setLastDayIncome(Object lastDayIncome) {
        this.lastDayIncome = lastDayIncome;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getKeepAmount() {
        return keepAmount;
    }

    public void setKeepAmount(double keepAmount) {
        this.keepAmount = keepAmount;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRedemption() {
        return redemption;
    }

    public void setRedemption(String redemption) {
        this.redemption = redemption;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    public Object getRedemptionID() {
        return redemptionID;
    }

    public void setRedemptionID(Object redemptionID) {
        this.redemptionID = redemptionID;
    }

    public long getFreshDate() {
        return freshDate;
    }

    public void setFreshDate(long freshDate) {
        this.freshDate = freshDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPureAmount() {
        return pureAmount;
    }

    public void setPureAmount(double pureAmount) {
        this.pureAmount = pureAmount;
    }

    public Object getAwaitDays() {
        return awaitDays;
    }

    public void setAwaitDays(Object awaitDays) {
        this.awaitDays = awaitDays;
    }

    public double getShowRate() {
        return showRate;
    }

    public void setShowRate(double showRate) {
        this.showRate = showRate;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public double getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(double applyAmount) {
        this.applyAmount = applyAmount;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Object getRemarkB() {
        return remarkB;
    }

    public void setRemarkB(Object remarkB) {
        this.remarkB = remarkB;
    }

    public long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(long applyDate) {
        this.applyDate = applyDate;
    }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }

    public Object getRedemptionDate() {
        return redemptionDate;
    }

    public void setRedemptionDate(Object redemptionDate) {
        this.redemptionDate = redemptionDate;
    }

    public Object getRedemptionAmount() {
        return redemptionAmount;
    }

    public void setRedemptionAmount(Object redemptionAmount) {
        this.redemptionAmount = redemptionAmount;
    }

    public Object getRedemptionTarget() {
        return redemptionTarget;
    }

    public void setRedemptionTarget(Object redemptionTarget) {
        this.redemptionTarget = redemptionTarget;
    }

    public Object getRedemptionBank() {
        return redemptionBank;
    }

    public void setRedemptionBank(Object redemptionBank) {
        this.redemptionBank = redemptionBank;
    }

    public Object getRedemptionCardNo() {
        return redemptionCardNo;
    }

    public void setRedemptionCardNo(Object redemptionCardNo) {
        this.redemptionCardNo = redemptionCardNo;
    }

    public Object getRedemptionBankName() {
        return redemptionBankName;
    }

    public void setRedemptionBankName(Object redemptionBankName) {
        this.redemptionBankName = redemptionBankName;
    }

    public Object getDeadline() {
        return deadline;
    }

    public void setDeadline(Object deadline) {
        this.deadline = deadline;
    }

    public Object getExpireTips() {
        return expireTips;
    }

    public void setExpireTips(Object expireTips) {
        this.expireTips = expireTips;
    }

    public Object getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(Object insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    public double getExpectIncomeAmount() {
        return expectIncomeAmount;
    }

    public void setExpectIncomeAmount(double expectIncomeAmount) {
        this.expectIncomeAmount = expectIncomeAmount;
    }

    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public int getOpe_type() {
        return ope_type;
    }

    public void setOpe_type(int ope_type) {
        this.ope_type = ope_type;
    }

    public String getNavDate() {
        return navDate;
    }

    public void setNavDate(String navDate) {
        this.navDate = navDate;
    }

    public Object getFundRevenuePerMillion() {
        return fundRevenuePerMillion;
    }

    public void setFundRevenuePerMillion(Object fundRevenuePerMillion) {
        this.fundRevenuePerMillion = fundRevenuePerMillion;
    }

    public Object getExpectLastIncome() {
        return expectLastIncome;
    }

    public void setExpectLastIncome(Object expectLastIncome) {
        this.expectLastIncome = expectLastIncome;
    }

    public double getLatestIncome() {
        return latestIncome;
    }

    public void setLatestIncome(double latestIncome) {
        this.latestIncome = latestIncome;
    }

    public String getS_merchant() {
        return s_merchant;
    }

    public void setS_merchant(String s_merchant) {
        this.s_merchant = s_merchant;
    }

    public Object getCalcInterestTimeStr() {
        return calcInterestTimeStr;
    }

    public void setCalcInterestTimeStr(Object calcInterestTimeStr) {
        this.calcInterestTimeStr = calcInterestTimeStr;
    }

    public Object getExtA() {
        return extA;
    }

    public void setExtA(Object extA) {
        this.extA = extA;
    }

    public Object getExtB() {
        return extB;
    }

    public void setExtB(Object extB) {
        this.extB = extB;
    }

    public Object getExtC() {
        return extC;
    }

    public void setExtC(Object extC) {
        this.extC = extC;
    }

    public Object getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(Object insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getItemSpecCode() {
        return itemSpecCode;
    }

    public void setItemSpecCode(String itemSpecCode) {
        this.itemSpecCode = itemSpecCode;
    }

    public Object getRedempting() {
        return redempting;
    }

    public void setRedempting(Object redempting) {
        this.redempting = redempting;
    }

    public Object getRedemptAmount() {
        return redemptAmount;
    }

    public void setRedemptAmount(Object redemptAmount) {
        this.redemptAmount = redemptAmount;
    }

    public int getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(int salesChannel) {
        this.salesChannel = salesChannel;
    }

    public String getUrlTransfer() {
        return urlTransfer;
    }

    public void setUrlTransfer(String urlTransfer) {
        this.urlTransfer = urlTransfer;
    }

    public Object getYyRedemptionFlag() {
        return yyRedemptionFlag;
    }

    public void setYyRedemptionFlag(Object yyRedemptionFlag) {
        this.yyRedemptionFlag = yyRedemptionFlag;
    }

    public Object getNofeetimeDataType() {
        return nofeetimeDataType;
    }

    public void setNofeetimeDataType(Object nofeetimeDataType) {
        this.nofeetimeDataType = nofeetimeDataType;
    }

    public Object getCurMonthRedempted() {
        return curMonthRedempted;
    }

    public void setCurMonthRedempted(Object curMonthRedempted) {
        this.curMonthRedempted = curMonthRedempted;
    }

    public Object getInsTransfer() {
        return insTransfer;
    }

    public void setInsTransfer(Object insTransfer) {
        this.insTransfer = insTransfer;
    }

    public Object getRedemptionPayInfo() {
        return redemptionPayInfo;
    }

    public void setRedemptionPayInfo(Object redemptionPayInfo) {
        this.redemptionPayInfo = redemptionPayInfo;
    }

    public Object getDividentStr() {
        return dividentStr;
    }

    public void setDividentStr(Object dividentStr) {
        this.dividentStr = dividentStr;
    }

    public int getDistinctCode() {
        return distinctCode;
    }

    public void setDistinctCode(int distinctCode) {
        this.distinctCode = distinctCode;
    }

    public int getRef_fund_purchasetype() {
        return ref_fund_purchasetype;
    }

    public void setRef_fund_purchasetype(int ref_fund_purchasetype) {
        this.ref_fund_purchasetype = ref_fund_purchasetype;
    }

    public long getMaxCreated() {
        return maxCreated;
    }

    public void setMaxCreated(long maxCreated) {
        this.maxCreated = maxCreated;
    }

    public String getSystemSerial() {
        return systemSerial;
    }

    public void setSystemSerial(String systemSerial) {
        this.systemSerial = systemSerial;
    }

    public Object getTodayIncome() {
        return todayIncome;
    }

    public void setTodayIncome(Object todayIncome) {
        this.todayIncome = todayIncome;
    }

    public double getConfirmShare() {
        return confirmShare;
    }

    public void setConfirmShare(double confirmShare) {
        this.confirmShare = confirmShare;
    }

    public double getConfirmNetValue() {
        return confirmNetValue;
    }

    public void setConfirmNetValue(double confirmNetValue) {
        this.confirmNetValue = confirmNetValue;
    }

    public Object getConfirmAmount() {
        return confirmAmount;
    }

    public void setConfirmAmount(Object confirmAmount) {
        this.confirmAmount = confirmAmount;
    }

    public Object getRedemptionDateStr() {
        return redemptionDateStr;
    }

    public void setRedemptionDateStr(Object redemptionDateStr) {
        this.redemptionDateStr = redemptionDateStr;
    }

    public String getApplyDateString() {
        return applyDateString;
    }

    public void setApplyDateString(String applyDateString) {
        this.applyDateString = applyDateString;
    }

    public String getFreshDateString() {
        return freshDateString;
    }

    public void setFreshDateString(String freshDateString) {
        this.freshDateString = freshDateString;
    }

    public String getShortDateString() {
        return shortDateString;
    }

    public void setShortDateString(String shortDateString) {
        this.shortDateString = shortDateString;
    }

    public Object getRedemptionD() {
        return redemptionD;
    }

    public void setRedemptionD(Object redemptionD) {
        this.redemptionD = redemptionD;
    }

    public String getNavDateString() {
        return navDateString;
    }

    public void setNavDateString(String navDateString) {
        this.navDateString = navDateString;
    }

    public List<?> getRedemptionInfoList() {
        return redemptionInfoList;
    }

    public void setRedemptionInfoList(List<?> redemptionInfoList) {
        this.redemptionInfoList = redemptionInfoList;
    }

    public List<?> getTranBuyDetailSonList() {
        return tranBuyDetailSonList;
    }

    public void setTranBuyDetailSonList(List<?> tranBuyDetailSonList) {
        this.tranBuyDetailSonList = tranBuyDetailSonList;
    }

    public List<OperateListBean> getOperateList() {
        return operateList;
    }

    public void setOperateList(List<OperateListBean> operateList) {
        this.operateList = operateList;
    }

    public List<ExtInfoListBean> getExtInfoList() {
        return extInfoList;
    }

    public void setExtInfoList(List<ExtInfoListBean> extInfoList) {
        this.extInfoList = extInfoList;
    }

    public static class OperateListBean {
        /**
         * operateName : redemption
         * operateYn : 1
         */

        private String operateName;
        private int operateYn;

        public String getOperateName() {
            return operateName;
        }

        public void setOperateName(String operateName) {
            this.operateName = operateName;
        }

        public int getOperateYn() {
            return operateYn;
        }

        public void setOperateYn(int operateYn) {
            this.operateYn = operateYn;
        }
    }

    public static class ExtInfoListBean {
        /**
         * extKey : needIncomeDate
         * extValue : 2020-07-08
         */

        private String extKey;
        private String extValue;

        public String getExtKey() {
            return extKey;
        }

        public void setExtKey(String extKey) {
            this.extKey = extKey;
        }

        public String getExtValue() {
            return extValue;
        }

        public void setExtValue(String extValue) {
            this.extValue = extValue;
        }
    }
}
