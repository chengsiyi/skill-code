package com.chengsy.code.tools.moxi;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyExcel 操作工具类(写,追加)
 *
 * @author chengsiyi
 * @date 2022/9/29 13:11
 */
public class EasyExcelUtils {
    private ExcelWriter excelWriter = null;
    private WriteSheet writeSheet = null;

    /**
     * ===========================================================
     * <p>
     * Purpose      :   EasyExcel工具类 初始化(最先调用)
     *
     * @return Author       :   lzt
     * Created Date :   2021-12-28
     * Update History
     * Version       Date               Name            Description
     * --------  ---------------   --------------  --------------------
     * V1.0       2021-12-28           lzt            Creation
     * ===========================================================
     * @params: absFilePath  绝对路径
     * @params: sheetName 标签页名字
     * @params: titleList 标题头(第一行)
     */
    public void init(String absFilePath, String sheetName, List<String> titleList) {
        if (excelWriter == null && writeSheet == null) {
            List<List<String>> heads = new ArrayList<>(1);
            //表格头标题
            heads.add(titleList);
            // 这里 需要指定写用哪个标题头去写 可以用class 也可以不用
            excelWriter = EasyExcelFactory.write(absFilePath).head(heads).build();
            // 这里注意 如果同一个sheet只要创建一次
            writeSheet = EasyExcelFactory.writerSheet(sheetName).build();
        }
    }

    public <T> void init(String absFilePath, String sheetName, Class<T> clazz) {
        if (excelWriter == null && writeSheet == null) {
            //表格头标题
            excelWriter = EasyExcelFactory.write(absFilePath).head(clazz).build();
            // 这里注意 如果同一个sheet只要创建一次
            writeSheet = EasyExcelFactory.writerSheet(sheetName).build();
        }
    }

    /**
     * ===========================================================
     * <p>
     * Purpose      :   EasyExcel工具类 写入excel写内容
     *
     * @return Author       :   lzt
     * Created Date :   2021-12-28
     * Update History
     * Version       Date               Name            Description
     * --------  ---------------   --------------  --------------------
     * V1.0       2021-12-28           lzt            Creation
     * ===========================================================
     * @params: dataList  要插入的数据(多行插入)
     */
    public <T> void doExportExcel(List<T> dataList) {
        try {
            excelWriter.write(dataList, writeSheet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ===========================================================
     * <p>
     * Purpose      :   EasyExcel工具类 关闭(最后调用 关闭流)
     *
     * @return Author       :   lzt
     * Created Date :   2021-12-28
     * Update History
     * Version       Date               Name            Description
     * --------  ---------------   --------------  --------------------
     * V1.0       2021-12-28           lzt            Creation
     * ===========================================================
     */
    public void finish() {
        if (excelWriter != null) {
            excelWriter.finish();
        }
    }

    public static void main(String[] args) {
        EasyExcelUtils easyExcelUtil = new EasyExcelUtils();
        //使用原文件名在java的临时文件夹中创建临时文件
        File file = new File(System.getProperty("java.io.tmpdir"), "测试.xlsx");
        //若临时文件夹中已经存在该文件，则先删除
        if (file.exists()) {
            file.delete();
        }
        String absFilePath = file.getAbsolutePath();
        String title = "sheet第1页";
        List<String> lsCol = new ArrayList<>(1);
        lsCol.add("学生");
        lsCol.add("年龄");
        lsCol.add("性别");
        //初始化
        easyExcelUtil.init(absFilePath, title, lsCol);
        //写入数据
        List<List<String>> sumDataList = new ArrayList<>(1);
        List<String> dataList = new ArrayList<>(1);
        dataList.add("张三");
        dataList.add("16");
        dataList.add("女");
        sumDataList.add(dataList);
        //我的追加数据 其实是伪追加 因为只要你没有把流关闭 还是持有这个流的 那么你就可以一直调用这个方法 往里面写数据
        easyExcelUtil.doExportExcel(sumDataList);
        //关闭流
        easyExcelUtil.finish();
    }
}

