package daomain;

import java.sql.Time;

/**
 * @Author Cheng fu
 * @Date 2025/4/1 18:45
 */
public class Order {
    private String username;
    private int dishCount;
    private double price;
    private Time orderTime;
    
    public Order() {}
    
    public Order(String username, int dishCount, double price, Time orderTime) {
        this.username = username;
        this.dishCount = dishCount;
        this.price = price;
        this.orderTime = orderTime;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getDishCount() {
        return dishCount;
    }
    
    public void setDishCount(int dishCount) {
        this.dishCount = dishCount;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public Time getOrderTime() {
        return orderTime;
    }
    
    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }
}
