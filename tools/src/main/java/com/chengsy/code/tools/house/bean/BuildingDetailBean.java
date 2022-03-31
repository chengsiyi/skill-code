package com.chengsy.code.tools.house.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author chengsiyi
 * @date 2021/4/28 12:58
 */
public class BuildingDetailBean {

    @ExcelProperty("楼栋")
    private String buildingName;
    /**
     * 单元
     */
    @ExcelProperty("单元")
    private String unit;
    /**
     * 楼层
     */
    @ExcelProperty("楼层")
    @JSONField(name = "flr")
    private String floor;

    /**
     * 房间号
     */
    @ExcelProperty("房间号")
    @JSONField(name = "rn")
    private String roomName;

    /**
     * 公摊面积
     */
    @ExcelProperty("公摊面积")
    private Double sArea;

    /**
     * 建筑面积
     */
    @ExcelProperty("建筑面积")
    private Double bArea;

    /**
     * 套内面积
     */
    @ExcelProperty("套内面积")
    private Double iArea;

    /**
     * 套内价格
     */
    @ExcelProperty("套内价格")

    private Double nsjg;
    /**
     * 建面价格
     */
    @ExcelProperty("建面价格")

    private Double nsjmjg;

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }


    public Double getsArea() {
        return sArea;
    }

    public void setsArea(Double sArea) {
        this.sArea = sArea;
    }

    public Double getbArea() {
        return bArea;
    }

    public void setbArea(Double bArea) {
        this.bArea = bArea;
    }

    public Double getiArea() {
        return iArea;
    }

    public void setiArea(Double iArea) {
        this.iArea = iArea;
    }

    public Double getNsjg() {
        return nsjg;
    }

    public void setNsjg(Double nsjg) {
        this.nsjg = nsjg;
    }

    public Double getNsjmjg() {
        return nsjmjg;
    }

    public void setNsjmjg(Double nsjmjg) {
        this.nsjmjg = nsjmjg;
    }
}
