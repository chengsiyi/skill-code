package com.chengsy.code.tools.moxi;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

/**
 * @author chengsiyi
 * @date 2022/9/28 22:52
 */

public class Prize {

    @ColumnWidth(35)
    @ExcelProperty(value = "用户id", index = 0)
    private String userId;
    @ColumnWidth(25)
    @ExcelProperty(value = "奖品名称", index = 1)
    private String prizeName;
    @ColumnWidth(15)
    @ExcelProperty(value = "手机号", index = 2)
    private String phone;
    @ColumnWidth(20)
    @ExcelProperty(value = "获奖时间", index = 3)
    private String getTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
