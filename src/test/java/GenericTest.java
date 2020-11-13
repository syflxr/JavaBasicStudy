import javafx.util.Pair;

import java.util.ArrayList;

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

    void genericTest1(){
        Parent<Integer> p=new Child<Integer>();
        Number n=new Integer(7);
        //Parent<Number> wrong=new Child<Integer>();
        //上面这种写法错误

        //不定义泛型类型，实际上为object
        Parent obj=new Parent();

    }
}
