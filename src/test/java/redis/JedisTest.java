package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Auther: shenyafeng
 * @Date: 2021/1/2 16:17
 * @Description:Jedis基本功能使用
 */

public class JedisTest {

    @Test
    public void jedisTest(){
        //获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //操作
        jedis.set("username","syf");
        //关闭
        jedis.close();
    }
    @Test
    public void deleteTest(){
        //获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //删除不存在的key
        Long count = jedis.del("syf");
        System.out.println(count);//成功删除的个数，0
        //关闭
        jedis.close();
    }

    @Test
    public void get(){
        Jedis jedis=new Jedis("117.50.10.66",6379);
        jedis.set("syf","syf");
        String syf = jedis.get("syf");
        System.out.println(syf);

    }


}
