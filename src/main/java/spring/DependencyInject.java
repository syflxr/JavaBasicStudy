package spring;/*
 *@author
 *@description 测试依赖注入
 *@date
 */

import java.util.Date;

public class DependencyInject {
    private String name;
    private Integer age;
    private Date date;
    public DependencyInject(){}
    public DependencyInject(String name,Integer age,Date date){
        this.name=name;
        this.age=age;
        this.date=date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}