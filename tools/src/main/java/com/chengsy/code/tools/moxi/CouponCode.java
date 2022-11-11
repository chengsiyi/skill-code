package com.chengsy.code.tools.moxi;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author chengsiyi
 * @date 2022/9/30 16:53
 */
@Data
public class CouponCode {
    @ExcelProperty(value = "卡号", index = 0)
    private String code;
    @ExcelProperty(value = "密码", index = 1)
    private String pwd;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
