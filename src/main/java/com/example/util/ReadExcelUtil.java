package com.example.util;


import jxl.Sheet;
import jxl.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengqian
 * jxl
 * date 2018/4/23 15:09
 */
public class ReadExcelUtil {
    /**
     * 读取Excel文件。格式.xls
     */
    public static List<List<String>> excelDataList(String filePath) {
        List<List<String>> outerList = new ArrayList<List<String>>();
        try {
            File file = new File(filePath);
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file);
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // 获取Sheet对象
            Sheet sheet = wb.getSheet(0);
            // sheet.getRows()返回该页的总行数
            for (int i = 0; i < sheet.getRows(); i++) {
                List<String> innerList = new ArrayList();
                // sheet.getColumns()返回该页的总列数
                for (int j = 0; j < sheet.getColumns(); j++) {
                    String cellinfo = sheet.getCell(j, i).getContents();
                    if (cellinfo.isEmpty()) {
                        continue;
                    }
                    innerList.add(cellinfo);
                }
                outerList.add(i, innerList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outerList;

    }

    public static void main(String[] args) {
        List<List<String>> list = excelDataList("H:\\students.xls");
        System.out.println(list);
    }
}
