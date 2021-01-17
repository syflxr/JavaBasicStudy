package Utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: shenyafeng
 * @Date: 2021/1/17 19:38
 * @Description:
 */

public class RedisUtil {

    private static JedisPool jedisPool;
    private static final String REDIS_HOST_VALUE;
    private static final String REDIS_PORT_VALUE;
    private static GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();


    static{
        REDIS_HOST_VALUE=SettingUtil.get("REDIS_HOST","127.0.0.1");
        REDIS_PORT_VALUE=SettingUtil.get("REDIS_PORT","6379");
        System.out.println(REDIS_HOST_VALUE+" "+REDIS_PORT_VALUE);
        jedisPool= new JedisPool(poolConfig, REDIS_HOST_VALUE, Integer.parseInt(REDIS_PORT_VALUE));
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getJedis();
        jedis.setex("syf10", 10,"syf");
        System.out.println(jedis.get("syf"));
    }
}
