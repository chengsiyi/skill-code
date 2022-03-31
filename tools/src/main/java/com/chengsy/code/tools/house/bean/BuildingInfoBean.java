package com.chengsy.code.tools.house.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author chengsiyi
 * @date 2021/4/28 11:29
 */
public class BuildingInfoBean {

    @JSONField(name = "projectname")
    private String projectName;

    @JSONField(name = "blockname")
    private String blockName;

    @JSONField(name = "buildingid")
    private String buildingId;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }
}
