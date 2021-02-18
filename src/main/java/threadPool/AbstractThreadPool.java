package threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: shenyafeng
 * @Date: 2021/2/5 11:12
 * @Description:
 */

public class AbstractThreadPool {
    private static final Logger logger= LoggerFactory.getLogger(AbstractThreadPool.class);
    private static final CopyOnWriteArrayList<AbstractThreadPool> thisList=new CopyOnWriteArrayList<>();

    private volatile ThreadPoolExecutor threadPool = null;

    private volatile String name=null;

    protected AbstractThreadPool(ThreadPoolExecutor threadPool, String name){

        this.threadPool = threadPool;
        this.name = name;
        thisList.add(this);

        logger.info("初始化线程池 name="+name+" size="+thisList.size());
    }

    private static void createMonitorThread(){
        Thread monitorThread=new Thread(()->{
            while (true){
                //遍历thisList里的元素

                for(AbstractThreadPool thisPool:thisList){
                    try{
                        String thisName=thisPool.getName();
                        ThreadPoolExecutor thisTmpPool=thisPool.getThreadPool();
                        long taskCount=thisTmpPool.getTaskCount();
                        long completedTaskCount= thisTmpPool.getCompletedTaskCount();
                        long unCompletedTaskCount=taskCount-completedTaskCount;
                        logger.info("线程池监控 名称为【"+thisName+"】"
                                + " 总数=" + taskCount
                                + " 已完成=" + completedTaskCount
                                + " 未完成=" + unCompletedTaskCount
                                +" activeCount="+thisTmpPool.getActiveCount()
                                +" largestestPoolSize="+thisTmpPool.getLargestPoolSize()
                                + " poolSize="+thisTmpPool.getPoolSize()
                        );
                    } catch (Exception e){
                        logger.error("线程池监控异常",e);
                    }
                }
            }
        });
    }

    private ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
