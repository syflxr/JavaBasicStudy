package spring.service;

import org.springframework.stereotype.Service;

/**
 * @Auther: shenyafeng
 * @Date: 2020/12/12 00:12
 * @Description:
 */


@Service
public class OrderService1 implements IOrderService{
    @Override
    public int countAllOrders() {
        System.out.println("OrderService1");
        return 0;
    }
}
