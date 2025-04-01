package service;

import dao.OrderDao;

import java.time.LocalDateTime;

/**
 * 这是一个订单服务类，用于处理订单相关的业务逻辑。
 * @Author Cheng fu
 * @Date 2025/4/1 19:09
 */
public class OrderService {
    private OrderDao orderDao = new OrderDao();
    
    public boolean addOrder(String username, int dishCount, double price, LocalDateTime orderTime) {
        String sql = "insert into orders(username,dishCount,price,orderTime) values(?,?,?,?)";
        return orderDao.update(sql,username,dishCount,price,orderTime) > 0;
    }
    
    
}
