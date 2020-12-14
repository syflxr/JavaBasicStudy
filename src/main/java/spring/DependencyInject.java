package spring;/*
 *@author
 *@description 测试依赖注入
 *@date
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import spring.service.order.IOrderService;
import spring.service.order2implements.IOrderService2implements;

import javax.annotation.Resource;
import java.util.Date;

public class DependencyInject {
    private String name;
    private Integer age;
    private Date date;

    @Autowired
    IOrderService orderService;

    @Autowired
    IOrderService2implements orderService2implements1;

    @Autowired
    IOrderService2implements orderService2implements2;

    @Autowired
    @Qualifier("orderService2implements1")
    IOrderService2implements orderService2implements3;

    @Resource(name="orderService2implements2")
    IOrderService2implements orderService2implements4;

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

    public int getOrderCount(){
        return orderService.countAllOrders();
    }

    public void testAutowired(){
        orderService2implements1.countAllOrders();//orderService2implements1
        orderService2implements2.countAllOrders();//orderService2implements2
        orderService2implements3.countAllOrders();//orderService2implements1
        orderService2implements4.countAllOrders();//orderService2implements2
    }
}