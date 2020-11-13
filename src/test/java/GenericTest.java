import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * 功能描述:泛型测试
 * @param:
 * @return:
 * @auther: shenyafeng
 * @date: 2020/11/13 10:14
 */
public class GenericTest {
    static class Parent<T> {
        private T first;
        private T last;
        //static T t;不可用于静态变量和静态方法，应该与初始化时期有关，静态变量可以理解为与类无关的
        /*public static Pair<T> create(T first,T second){
            return new Pair<T>(first,second);
        }*/

        /**
         * 功能描述:该方法中的T和类中的T没有任何关系，它可以换成其它任意字母
         * @param: [first, second]
         * @return: javafx.util.Pair<T,T>
         * @auther: shenyafeng
         * @date: 2020/11/13 13:18
         */
        public static <T> Pair<T,T> create(T first, T second){
            return new Pair(first,second);
        }

        public T getFirst(){
            return first;
        }

        public T getLast(){
            return last;
        }


    }
    static class Child<T> extends Parent<T>{

    }

    /**
     * 功能描述:测试获取泛型的类型，实际上都是Pair<Object>
     * @param: []
     * @return: void
     * @auther: shenyafeng
     * @date: 2020/11/13 16:51
     */
    @Test
    public void GenericClassTest(){
        Parent<Integer> p1=new Parent<>();
        Parent<Long> p2=new Parent<>();
        System.out.println(p1.getClass()==p2.getClass());//true
        System.out.println(p1.getClass()==Parent.class);//true

        if(Parent<Integer>.class==Parent.class){
            System.out.println(true);
        }
        if(p1 instanceof Parent<Integer>){
            System.out.println(true);
        }
    }

    @Test
    public void genericTest1(){
        Parent<Integer> p=new Child<Integer>();
        Number n=new Integer(7);
        //Parent<Number> wrong=new Child<Integer>();
        //上面这种写法错误

        //不定义泛型类型，实际上为object
        Parent obj=new Parent();

    }



}
