package com.chengsy.code.tools;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chengsiyi
 * @date 2020/4/23 19:21
 */
public class Fund {
    private static final Logger logger = LoggerFactory.getLogger(Fund.class);
    private static final String dailyData = "https://fundmobapi.eastmoney.com/FundMApi/FundVarietieValuationDetail.ashx?FCODE=%s&deviceid=Wap&plat=Wap&product=EFund&version=2.0.0&_=%d";

    public static void main(String[] args) {
//        jdGet();
        String url = String.format(dailyData, "320007", System.currentTimeMillis());
        HttpUtil.sendGet(url);
//        monthIncome();
//        sevenminute();
//        EasyExcel.write()
        //https://fundmobapi.eastmoney.com/FundMApi/FundYieldDiagramNew.ashx?FCODE=320007&RANGE=y&deviceid=Wap&plat=Wap&product=EFund&version=2.0.0&_=

        //https://fundmobapi.eastmoney.com/FundMApi/FundNetDiagram.ashx?FCODE=320007&RANGE=y&deviceid=Wap&plat=Wap&product=EFund&version=2.0.0&_=
        //https://fundmobapi.eastmoney.com/FundMApi/FundVarietieValuationDetail.ashx?FCODE=320007&deviceid=Wap&plat=Wap&product=EFund&version=2.0.0&_=
        }

    public static void jdGet() {
        String urlPath = "http://trade.jr.jd.com/ajaxFinance/financeMainDataNew.action?type=2&_dc=1596029730945";
        logger.info("请求响应结果:{}", doGet(urlPath,null));
    }

    public static Map<String, Double> dailyIncome() {
        String url = "https://trade.jr.jd.com/centre/getOverviewInData.action?&_dc=1596683329678";
        String result = doGet(url, null);
        IncomeDataBO incomeData = JSON.parseObject(result, new TypeReference<IncomeDataBO>() {
        });
        List<IncomeDataBO.InnerIncomeDataBO> dailyIncome = incomeData.getIncomeData();
        return dailyIncome.stream().collect(Collectors.toMap(IncomeDataBO.InnerIncomeDataBO::getDate, IncomeDataBO.InnerIncomeDataBO::getIncome));
    }

    public static void monthIncome() {
        Map<String, Double> result = dailyIncome();
        Double sum = 0.0;
        for (Map.Entry<String, Double> stringDoubleEntry : result.entrySet()) {
            sum +=stringDoubleEntry.getValue();
        }
        logger.info("总收益:{}", sum);
    }

    public static void sevenminute(){
        String url = "https://mobile.7min.com.cn/trade/fund/share/list?virtualAccountCode=";
        doGet(url, null);

    }

    public static class IncomeDataBO {

        private Integer maxIncome;
        List<InnerIncomeDataBO> incomeData;

        public Integer getMaxIncome() {
            return maxIncome;
        }

        public void setMaxIncome(Integer maxIncome) {
            this.maxIncome = maxIncome;
        }

        public List<InnerIncomeDataBO> getIncomeData() {
            return incomeData;
        }

        public void setIncomeData(List<InnerIncomeDataBO> incomeData) {
            this.incomeData = incomeData;
        }

        class InnerIncomeDataBO {
            private String date;
            private Double income;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public Double getIncome() {
                return income;
            }

            public void setIncome(Double income) {
                this.income = income;
            }
        }
    }

    private static String doGet(String urlPath, String cookie) {
        try {
            URL url = new URL(urlPath);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Cookie", cookie);
            conn.setDoInput(true);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            logger.info("Fund-doGet 请求参数:urlPath = [{}], 响应 = [{}]", urlPath, result);
            return result.toString();
        } catch (IOException e) {
            logger.error("Fund-doGet 请求参数:urlPath = [{}]", urlPath, e);
        }
        return null;
    }





}
