package spring;/*
 *@author
 *@description 练习spring ioc
 *@date
 */

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIoc {
    public static void main(String[] args) {
        ApplicationContext context = (ApplicationContext) new ClassPathXmlApplicationContext("bean.xml");
        UserDataDao userDataDao1 = (UserDataDao) context.getBean("userDataDao");
        UserDataDao userDataDao2 = (UserDataDao) context.getBean("userDataDao");//需要有无参构造函数才能成功
        System.out.println(userDataDao1 + " " + userDataDao2);//地址相同，默认是单例的
    }

    @Test
    public void dependInjectTest(){
        ApplicationContext context = (ApplicationContext) new ClassPathXmlApplicationContext("bean.xml");
        DependencyInject dependencyInject=(DependencyInject) context.getBean("dependencyInject");
        System.out.println(dependencyInject.getAge()+" "+dependencyInject.getName()+" "+dependencyInject.getDate());
    }
}
