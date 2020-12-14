package spring.service.order2implements;

import org.springframework.stereotype.Service;

/**
 * @Auther: shenyafeng
 * @Date: 2020/12/13 21:44
 * @Description:
 */

@Service
public class OrderService2implements2 implements IOrderService2implements{
    @Override
    public int countAllOrders() {
        System.out.println("OrderService2implements2");
        return 0;
    }
}
