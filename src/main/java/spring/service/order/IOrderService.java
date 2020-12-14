package spring.service.order;

/**
 * @Auther: shenyafeng
 * @Date: 2020/12/12 00:11
 * @Description:该接口只有一个实现类，autowired时候不需要指定bean的id
 */
public interface IOrderService {
    int countAllOrders();
}
