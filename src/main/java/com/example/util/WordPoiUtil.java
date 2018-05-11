package com.example.util;

import com.example.util.Vo.NoticeVo;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fengqian
 * date 2018/5/3 15:37
 */
public class WordPoiUtil {

    private static final String TEMPLATE_FILE_PATH = "H:\\";
    private static final String DEST_FILE_PATH = "H:\\";
    private static final String UNDERLINE = "_";
    private static final String SUFFIX = ".docx";

    public static void searchAndReplace(String srcPath, String destPath, Map<String, Object> map) {
        try {
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
            /**
             * 替换段落中的指定文字
             */
            Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
            while (itPara.hasNext()) {
                XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                Set<String> set = map.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    List<XWPFRun> run = paragraph.getRuns();
                    for (int i = 0; i < run.size(); i++) {
                        if (run.get(i).getText(run.get(i).getTextPosition()) != null &&
                                run.get(i).getText(run.get(i).getTextPosition()).equals(key)) {
                            /**
                             * 参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始
                             * 就可以把原来的文字全部替换掉了
                             */
                            run.get(i).setText(map.get(key).toString(), 0);
                        }
                    }
                }
            }

            /**
             * 替换表格中的指定文字
             */
            Iterator<XWPFTable> itTable = document.getTablesIterator();
            while (itTable.hasNext()) {
                XWPFTable table = (XWPFTable) itTable.next();
                //判断传入的数据类型中是否有list，如果有list，需要新增表格列，没有则直接替换参数内容
                Set<String> keys = map.keySet();
                boolean isExitList = false;
                for (String str : keys) {
                    if (map.get(str) instanceof List) {
                        isExitList = true;
                        ArrayList<String> rows = (ArrayList<String>) map.get(str);
                        for (int i = 0; i < rows.size(); i++) {
                            //插入一行
                            table.createRow();
                            //获取到刚刚插入的行
                            XWPFTableRow row = table.getRow(i + 1);
                            //设置单元格内容
                            String[] cols = rows.get(i).split(",");//每一行中列的值以，分割
                            for (int j = 0; j < cols.length; j++) {
                                row.getCell(j).setText(cols[j]);
                            }
                        }

                    }
                }
                if (isExitList == false) {
                    //不存在则是普通表格，直接替换
                    int count = table.getNumberOfRows();
                    for (int i = 0; i < count; i++) {
                        XWPFTableRow row = table.getRow(i);
                        List<XWPFTableCell> cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            for (Map.Entry<String, Object> e : map.entrySet()) {
                                if (cell.getText().equals(e.getKey())) {
                                    cell.removeParagraph(0);
                                    cell.setText(e.getValue().toString());
                                }
                            }
                        }
                    }
                }
            }
            FileOutputStream outStream = null;
            outStream = new FileOutputStream(destPath);
            document.write(outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFileName(String type) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssssss");
        String fileName = sdf.format(System.currentTimeMillis());
        return type + UNDERLINE + fileName + SUFFIX;
    }

    public static void main(String[] args) throws Exception {

        if (TemplateEnum.TEMPLATE_NOTICE.getType().equalsIgnoreCase("notice")) {
            List<String> stuList=new ArrayList<>();
            stuList.add("1,001,啦啦,500");
            stuList.add("2,002,哈哈,498");
            NoticeVo noticeVo = new NoticeVo("录取通知书", "啦啦", "北京交通大学", "2018-06-06",stuList);
            String srcPath = TEMPLATE_FILE_PATH + TemplateEnum.TEMPLATE_NOTICE.getTemPath();
            Map<String, Object> map = new HashMap<String, Object>();
            List<String> attrs = ClassUtil.getAllAttributes(noticeVo);
            for (int i = 0; i < attrs.size(); i++) {
                map.put("${" + attrs.get(i) + "}", ClassUtil.getGetMethod(noticeVo, attrs.get(i)));
            }
            String destPath = DEST_FILE_PATH + getFileName("notice");
            searchAndReplace(srcPath, destPath, map);
        }
    }

}