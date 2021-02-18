package threadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: shenyafeng
 * @Date: 2021/2/11 14:04
 * @Description:用于处理http请求
 */

public class HttpThreadPool extends AbstractThreadPool{
    protected HttpThreadPool(ThreadPoolExecutor threadPool, String name) {
        super(threadPool, name);
    }
    public static final HttpThreadPool INSTANCE;
    public static final ThreadPoolExecutor executor=new ThreadPoolExecutor(1,1,10, TimeUnit.SECONDS,new LinkedBlockingQueue<>(100),new ThreadPoolExecutor.CallerRunsPolicy());

    static{
        INSTANCE=new HttpThreadPool(executor,"http线程池");
    }
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}
