package com.chengsy.code;

import com.chengsy.code.tools.moxi.CouponCode;
import com.chengsy.code.tools.moxi.EasyExcelUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengsiyi
 * @date 2022/9/30 16:51
 */
public class EasyExcel {

    @Test
    public void genExcel() {
        Integer count = 200000;
        EasyExcelUtils utils = new EasyExcelUtils();
        utils.init(count + ".xlsx", "Sheet1", CouponCode.class);
        CouponCode code;
        List<CouponCode> codes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            code = new CouponCode();
            code.setCode(RandomStringUtils.randomAlphabetic(20));
            code.setPwd(RandomStringUtils.randomAlphabetic(50));
            codes.add(code);
            if (i % 100 == 0) {
                utils.doExportExcel(codes);
                codes.clear();
            }
        }
        if (!codes.isEmpty()){
            utils.doExportExcel(codes);
        }
        utils.finish();
    }
}
