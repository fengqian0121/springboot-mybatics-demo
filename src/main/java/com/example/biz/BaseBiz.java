package com.example.biz;

import com.example.util.ParseXMLUtil;
import com.example.util.ReadExcelPoiUtil;
import com.example.util.ReadExcelUtil;
import com.example.model.DbColumn;
import com.example.service.DbColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author fengqian
 * date 2018/4/23 15:46
 */
@Service
public class BaseBiz {
    @Autowired
    private DbColumnService dbColumnService;

    //将excel中数据根据xml文件列的对应关系，构建相应的数据库对象，存入数据库
    public void buildEntity(String planType, String filePath) {
        //获取excel文件数据
//        List<List<String>> excelDataList = ReadExcelUtil.excelDataList(filePath);//jxl
        List<List<String>> excelDataList = ReadExcelPoiUtil.getExcelList(filePath);//poi
        //获取匹配关系,实际开发中将此方法放于项目启动时，一启动就加载
//        ParseXMLUtil.planList();
        //根据planType获取excel列与数据表列的对应关系
        Map<String, String> mapping = ParseXMLUtil.plansMap.get(planType);
        for (int i = 1; i < excelDataList.size(); i++) {
            System.out.println(excelDataList.get(i));
            //定义用于构建实体的数组，存放属性值
            String[] attributes = new String[6];
            for (int j = 0; j < excelDataList.get(i).size(); j++) {
                attributes[Integer.parseInt(mapping.get(j + 1 + "")) - 1] = excelDataList.get(i).get(j);
            }
            DbColumn dbColumn = new DbColumn(attributes);
            dbColumnService.addDbColumn(dbColumn);
        }

    }

    public static void main(String[] args) {
        BaseBiz baseBiz = new BaseBiz();
        baseBiz.buildEntity("12001", "H:\\students.xls");
    }
}
