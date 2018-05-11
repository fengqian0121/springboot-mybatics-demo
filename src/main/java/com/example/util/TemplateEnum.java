package com.example.util;


/**
 * @author fengqian
 * date 2018/5/4 10:48
 */
public enum TemplateEnum {

    TEMPLATE_NOTICE("notice","template.docx")
    ;

    TemplateEnum(String type, String temPath) {
        this.type = type;
        this.temPath = temPath;
    }

    private String type;//通知函类型
    private String temPath;//模板路径

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemPath() {
        return temPath;
    }

    public void setTemPath(String temPath) {
        this.temPath = temPath;
    }


}
