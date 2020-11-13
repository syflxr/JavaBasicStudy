package CacheTest;

import com.google.common.cache.CacheBuilder;
import org.junit.Test;
import sun.misc.Cache;

/**
 * @Auther: shenyafeng
 * @Date: 2020/11/13 15:56
 * @Description:测试本地缓存Google Guava Cache用法
 * 大多数场景用不到，本地缓存只存在当前服务器的内存中，而服务肯定部署在不止一台机子上，因此在本地缓存的内容肯定不会需要和
 * 其它服务器因为需要保持一致性而一直更新，因此本地缓存存放的内容应该是不会变化的，只读的内容。
 */
public class LocalCacheTest {

    @Test
    public void CreateTest(){

    }

    public static void main(String[] args) {

    }
}
