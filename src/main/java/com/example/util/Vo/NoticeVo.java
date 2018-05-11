package com.example.util.Vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengqian
 * date 2018/5/9 13:45
 */
public class NoticeVo {

    private String title;
    private String name;
    private String school;
    private String date;
    private List<String> stuList=new ArrayList<>();

    public NoticeVo(String title, String name, String school, String date, List<String> stuList) {
        this.title = title;
        this.name = name;
        this.school = school;
        this.date = date;
        this.stuList = stuList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getStuList() {
        return stuList;
    }

    public void setStuList(List<String> stuList) {
        this.stuList = stuList;
    }
}
