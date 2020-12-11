import com.syf.Dao.UserDataDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.*;


public class UserDataDaoTest {
    @Autowired
    UserDataDao userDataDao;




    /**
     * 功能描述:StringBuilder类方法测试
     *
     * @param: []
     * @return: void
     * @auther: shenyafeng
     * @date: 2020/11/5 19:39
     */
    @Test
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder("abc");
        StringBuilder reverse = sb.reverse();
        System.out.println(reverse.toString());//cba
        System.out.println(sb);//cba
        sb.delete(0, 1);//前闭后开
        System.out.println(sb.toString());//ba
        char[] inserted = new char[]{'a', 'b', 'c'};
        sb.insert(1, inserted, 0, inserted.length);
        System.out.println(sb.toString());//b(abc)a
        sb.insert(1, "syf");
        System.out.println(sb);//bsyfabca
        sb.setCharAt(1, 'S');
        System.out.println(sb);//bSyfabca
    }

    /**
     * 功能描述:Random类方法测试
     *
     * @param: []
     * @return: void
     * @auther: shenyafeng
     * @date: 2020/11/5 19:39
     */
    @Test
    public void randomTest() {
        Random random = new Random();
        System.out.println(random.nextInt(10));

    }

    /**
     * 功能描述:Arrays类方法测试
     *
     * @param: []
     * @return: void
     * @auther: shenyafeng
     * @date: 2020/11/5 19:38
     */
    @Test
    public void arraysTest() {
        int[] arr = new int[]{2, 34, 35, 4, 657, 8, 69, 9};
        System.out.println(arr);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        int[] copy1 = Arrays.copyOf(arr, 5);
        System.out.println(Arrays.toString(copy1));// 2 4 8 9 32
        int[] copy2 = Arrays.copyOfRange(arr, 0, 5);
        System.out.println(Arrays.toString(copy2));// 2 4 8 9 34
        List<Integer> list = Arrays.asList(new Integer(1), new Integer(2), new Integer(3));
        List<Integer> arrayList = new ArrayList<>(list);
        System.out.println(list);//1 2 3
    }

    @Test
    public void collectionTest() {
        Map<Long, Long> map = new HashMap();
    }

    @Test
    public void springTest() throws Exception {
        Assert.isTrue(1 == 0, "wrong");
        System.out.println("finish");
    }

    /**
     * 功能描述:测试spring
     *
     * @param: []
     * @return: void
     * @auther: shenyafeng
     * @date: 2020/11/5 19:37
     */
    @Test
    public void SpringTest2() {
        try {
            springTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述:测试clone方法的类
     *
     * @auther: shenyafeng
     * @date: 2020/11/5 16:13
     */
    class TestClone1 implements Cloneable {
        public String str1 = "str1";
        public String str2 = "str2";

        public TestClone1 clone() throws CloneNotSupportedException {
            return (TestClone1) super.clone();
        }
    }

    class TestClone2 implements Cloneable {
        public String str1 = "str1";
        public String str2 = "str2";

        public TestClone2 clone() throws CloneNotSupportedException {
            TestClone2 testClone2 = new TestClone2();
            testClone2.str1 = new String(this.str1);
            testClone2.str2 = new String(this.str2);
            return testClone2;
        }
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        /**
         * 功能描述:测试clone的函数
         * @param: []
         * @return: void
         * @auther: shenyafeng
         * @date: 2020/11/5 16:27
         */
        TestClone1 test1 = new TestClone1();
        TestClone1 test1Clone = test1.clone();

        TestClone2 test2 = new TestClone2();
        TestClone2 test2Clone = test2.clone();
        System.out.println(test1.str1 == test1Clone.str1);
        System.out.println(test2.str1 == test2Clone.str1);
        System.out.println(test2.str1.equals(test2Clone.str1));
    }

    @FunctionalInterface
    public interface filter {
        public boolean filter(Integer integer);
    }

    /**
     * 功能描述:java8新特性测试
     *
     * @param: []
     * @return: void
     * @auther: shenyafeng
     * @date: 2020/11/5 19:41
     */
    @Test
    public void java8Test() throws Exception {
        Runnable runnable = () -> {
            System.out.println("函数式接口");
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        List<Integer> list = Arrays.asList(3, 4, 1, 5, 6, 3);
        Comparator<Integer> comparable = (i1, i2) -> {
            return i1 - i2;
        };
        list.sort(comparable);
        System.out.println(list);

        List<Integer> list1 = Arrays.asList(1, 5, 2, 7, 9, 1, 3, 45, 6, 2);
        List<Integer> result = filter(list1, i -> i > 6);
        System.out.println(result);//7 9 45

        List<Integer> result1 = biFilter(list1, (i, s) -> i > 2 && s.compareTo("9") < 0);
        System.out.println(result1);

        consumer(list1, (i) -> {
            System.out.print(i);
        });
        System.out.println("");
        biConsumer(list1, (i, s) -> System.out.print(i + "," + s + " "));
        System.out.println(functionTest(list1, (i) -> new String(i.toString())));

        TestClone1 t1 = new TestClone1();
        Runnable r = () -> {
            t1.str1 = "1";
        };
        String result2 = supplierTest(() -> {
            String str = new String("supply");
            return str;
        });
        System.out.println(result2);
    }

    /**
     * 功能描述:Predicate测试,详见java8新特性测试
     *
     * @param: [arr, p]
     * @return: java.util.List<java.lang.Integer>
     * @auther: shenyafeng
     * @date: 2020/11/6 10:37
     */
    public List<Integer> filter(List<Integer> arr, Predicate<Integer> p) {
        List<Integer> result = new ArrayList<>();
        for (Integer i : arr) {
            if (p.test(i)) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 功能描述:BiPredicate测试,详见java8新特性测试
     *
     * @param: [arr, biPredicate]
     * @return: java.util.List<java.lang.Integer>
     * @auther: shenyafeng
     * @date: 2020/11/6 10:38
     */
    public List<Integer> biFilter(List<Integer> arr, BiPredicate<Integer, String> biPredicate) {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer i : arr) {
            if (biPredicate.test(i, i.toString())) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 功能描述:Consumer测试,详见java8新特性测试
     *
     * @param: [arr, c]
     * @return: void
     * @auther: shenyafeng
     * @date: 2020/11/6 10:41
     */
    public void consumer(List<Integer> arr, Consumer<Integer> c) {
        for (Integer i : arr) {
            c.accept(i);
        }
    }

    public void biConsumer(List<Integer> arr, BiConsumer<Integer, String> biConsumer) {
        for (Integer i : arr) {
            biConsumer.accept(i, i.toString());
        }
    }

    public List<String> functionTest(List<Integer> arr, Function<Integer, String> f) {
        List<String> result = new ArrayList<>();
        for (Integer i : arr) {
            result.add(f.apply(i));
        }
        return result;
    }

    public String supplierTest(Supplier<String> supplier) {
        return supplier.get();
    }


    @Test
    public void fun() {
        System.out.println("i love lxr");
    }

    @Test
    public void testLong() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserDataDao userDataDao = (UserDataDao) context.getBean("userDataDao");
        userDataDao.getList(null).forEach(System.out::println);
        System.out.println(new Date());
    }

    public static String getPlatformRelatedDomain(String url) {
        try {
            return new URL(url).getHost();
        } catch (Exception e) {
        }
        return "";
    }

    @Test
    public void StringTest() {
        String url = "https://";
        String url1 = "https://a/";
        String url2 = "a/abc/";
        System.out.println(getPlatformRelatedDomain(url));
        System.out.println(getPlatformRelatedDomain(url1));
        System.out.println(getPlatformRelatedDomain(url2));

        String[] arr = new String[]{"1", "1", "1", "1", "1"};
        System.out.println(CollectionUtils.arrayToList(arr));
    }

}
