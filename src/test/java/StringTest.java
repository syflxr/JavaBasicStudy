import org.junit.Test;

import java.util.Arrays;

/**
 * @Auther: shenyafeng
 * @Date: 2020/11/13 10:13
 * @Description:测试String、String Builder以及一些常用组件
 */
public class StringTest {
    @Test
    public void testString() {
        String str = "abcabcabcabc";
        String firstResult=str.replaceFirst("abc","cde");//cdeabcabcabc
        System.out.println(firstResult);
        String result = str.replaceAll("abc", "cde");//cdecdecdecde
        System.out.println(result);//cdecdecdecde
        System.out.println(Arrays.toString(str.split("c")));//[ab, ab, ab, ab]
        System.out.println(str.substring(0, 2));//ab,startIndex不能小于0，endIndex不能超过字符串长度，startIndex不能大于endIndex，否则抛异常
        System.out.println(str.toLowerCase());
        System.out.println(str.toUpperCase());//ABCABCABCABC
        System.out.println(str.indexOf('b'));//1
        System.out.println(str.indexOf('b', 3));//4
        System.out.println(str.lastIndexOf('b'));
        System.out.println(str.lastIndexOf('b', 9));
        str = " " + str + " ";// abcabcabcabc

        System.out.println(str);
        System.out.println(str.trim());
        System.out.println(str.replace(".0", ""));
        System.out.println("2020-11-20 17:54:00.0".replace(".0", ""));
    }
}
