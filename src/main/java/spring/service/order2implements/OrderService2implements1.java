package spring.service.order2implements;

import org.springframework.stereotype.Service;

/**
 * @Auther: shenyafeng
 * @Date: 2020/12/12 00:12
 * @Description:
 */

@Service
public class OrderService2implements1 implements IOrderService2implements {
    @Override
    public int countAllOrders() {
        System.out.println("OrderService2implements1");
        return 0;
    }
}
