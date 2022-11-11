package com.chengsy.code.tools.house;

import cn.hutool.http.HttpUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chengsy.code.tools.house.bean.BuildingDetailBean;
import com.chengsy.code.tools.house.bean.BuildingInfoBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chengsiyi
 * @date 2021/4/27 20:20
 */
public class Price {

    public static final Logger logger = LoggerFactory.getLogger(Price.class);

    private static final String BUILDING_LIST_URL = "http://www.cq315house.com/WebService/WebFormService" +
            ".aspx/getParamDatas";
    private static final String SINGLE_BUILDING_INFO = "http://www.cq315house.com/WebService/WebFormService" +
            ".aspx/GetRoomJson";

    public static List<BuildingInfoBean> getBuildingList() {
        String body = "{\"siteid\":\"\",\"useType\":\"\",\"areaType\":\"\",\"projectname\":\"星瀚雅府\",\"entName\":\"\",\"location\":\"\",\"minrow\":\"1\",\"maxrow\":\"11\"}";
        String httpResult = HttpUtil.post(BUILDING_LIST_URL, body);
        if (StringUtils.isBlank(httpResult)) {
            return Collections.emptyList();
        }
        JSONObject json = JSON.parseObject(httpResult);
        List<BuildingInfoBean> result = new ArrayList<>();
        List<BuildingInfoBean> list = json.getJSONArray("d").toJavaList(BuildingInfoBean.class);
        for (BuildingInfoBean buildingInfoBean : list) {
            String[] blockNames = buildingInfoBean.getBlockName().split(",");
            String[] buildingIds = buildingInfoBean.getBuildingId().split(",");
            for (int i = 0; i < buildingIds.length; i++) {
                BuildingInfoBean info = new BuildingInfoBean();
                info.setProjectName(buildingInfoBean.getProjectName());
                info.setBlockName(blockNames[i]);
                info.setBuildingId(buildingIds[i]);
                result.add(info);
            }
        }
        return result;
    }

    public static List<BuildingDetailBean> getBuildingInfo(String buildingId,String buildingName) {
        JSONObject param = new JSONObject();
        param.put("buildingid", buildingId);
        String httpResult = HttpUtil.post(SINGLE_BUILDING_INFO, param.toJSONString());
        if (StringUtils.isBlank(httpResult)) {
            return Collections.emptyList();
        }
        JSONObject json = JSON.parseObject(httpResult);
        JSONArray array = json.getJSONArray("d");
        List<BuildingDetailBean> total = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject simple = array.getJSONObject(i);
            String name = simple.getString("name");
            JSONArray rooms = simple.getJSONArray("rooms");

            for (int j = 0; j < rooms.size(); j++) {
                JSONObject room = rooms.getJSONObject(i);
                BuildingDetailBean bean = room.toJavaObject(BuildingDetailBean.class);
                bean.setBuildingName(buildingName);
            }
//            toJavaList(BuildingDetailBean.class);
//            rooms.forEach(f -> f.setUnit(name));
//            total.addAll(rooms);
        }
        return total;
    }


    public static void main(String[] args) {
        List<BuildingInfoBean> buildingList = getBuildingList();
        for (BuildingInfoBean buildingInfo : buildingList) {
            List<BuildingDetailBean> single = getBuildingInfo(buildingInfo.getBuildingId(),buildingInfo.getBlockName());
            logger.info("楼栋:{}, 信息:{}", buildingInfo.getBlockName(), JSON.toJSONString(single));
//            ExcelWriter writer = EasyExcel.read();
        }
    }
}
