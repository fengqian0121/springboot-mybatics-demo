package com.example.util;

/**
 * @author fengqian
 * poi
 * date 2018/4/24 10:21
 */


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ReadExcelPoiUtil {

    private ReadExcelPoiUtil() {
    }
    private static int sheetNum = 0;//要解析的sheet下标

    /**
     * 根据excal路径生成实体集合
     */
    public static List<List<String>> getExcelList(String filePath) {
        try {
           File file=new File(filePath);
            return getExcelList(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据输入流生成集合
     */
    public static List<List<String>> getExcelList(File file)
            throws Exception {
        return ReadExcelPoiUtil.readExcel(file);
    }

    /**
     * Excel读取 操作
     */
    public static List<List<String>> readExcel(File file) {
        List<List<String>> outerList = new ArrayList<List<String>>();
        InputStream inStream=null;
        try {
            inStream=new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(inStream);
            HSSFSheet sheet = workbook.getSheetAt(sheetNum);//获得表
            int lastRowNum = sheet.getLastRowNum();//最后一行
            for (int i = 0; i <= lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);//获得行
                List<String> rowList = readExcelRow(row);
                outerList.add(rowList);

            }
        } catch (Exception e) {
            try {
                inStream=new FileInputStream(file);
                XSSFWorkbook workbook = new XSSFWorkbook(inStream);
                XSSFSheet sheet = workbook.getSheetAt(sheetNum);
                int lastRowNum = sheet.getLastRowNum();//最后一行,下标从0开始
                for (int i = 0; i <=lastRowNum; i++) {
                    XSSFRow row = sheet.getRow(i);//获得行
                    List<String> rowList = readExcelRow(row);
                    outerList.add(rowList);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            close(null, inStream);
        }
        return outerList;
    }

    /**
     * 读取行值 xls
     *
     * @return
     */
    private static List<String> readExcelRow(HSSFRow row) {
        List<String> rowList = new ArrayList<>();
        int lastCellNum = row.getPhysicalNumberOfCells();//获取总列数
        for (int i = 0; i < lastCellNum; i++) {
            HSSFCell cell = row.getCell(i);
            String cellVal = readCellValue(cell);
            rowList.add(cellVal);
        }
        return rowList;
    }

    /**
     * 读取行值 xlsx
     *
     * @return
     */
    private static List<String> readExcelRow(XSSFRow row) {
        List<String> rowList = new ArrayList<>();
        int lastCellNum =  row.getPhysicalNumberOfCells();//获取总列数
        for (int i = 0; i < lastCellNum; i++) {
            XSSFCell cell = row.getCell(i);
            String cellVal = readCellValue(cell);
            rowList.add(cellVal);
        }
        return rowList;
    }

    /**
     * 读取单元格的值 xls
     */
    private static String readCellValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            hssfCell.setCellType(1);//设置为String
            String str_temp = String.valueOf(hssfCell.getRichStringCellValue());//得到值
            return str_temp;
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_FORMULA) {
            int val = hssfCell.getCachedFormulaResultType();
            return val + "";
        } else {
            return String.valueOf(hssfCell.getRichStringCellValue());
        }
    }

    /**
     * 读取单元格的值 xlsx
     */
    private static String readCellValue(XSSFCell sssfCell) {
        if (sssfCell.getCellType() == sssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(sssfCell.getBooleanCellValue());
        } else if (sssfCell.getCellType() == sssfCell.CELL_TYPE_NUMERIC) {
            sssfCell.setCellType(1);//设置为String
            String str_temp = String.valueOf(sssfCell.getRichStringCellValue());//得到值
            return str_temp;
        } else if (sssfCell.getCellType() == sssfCell.CELL_TYPE_FORMULA) {
            int val = sssfCell.getCachedFormulaResultType();
            return val + "";
        } else {
            return String.valueOf(sssfCell.getRichStringCellValue());
        }
    }

    /**
     * 关闭io流
     */
    private static void close(OutputStream out, InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("InputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                System.out.println("OutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        List<List<String>> list = getExcelList("H:\\students.xls");
        System.out.println(list);
    }

}