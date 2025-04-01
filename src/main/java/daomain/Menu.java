package daomain;

/**
 * @Author Cheng fu
 * @Date 2025/4/1 18:48
 */
public class Menu {
    private String dishName;
    private double price;
    private int dishCount;
    
    public Menu() {}
    
    public Menu(String dishName, double price) {
        this.dishName = dishName;
        this.price = price;
    }
    
    public String getDishName() {
        return dishName;
    }
    
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getDishCount() {
        return dishCount;
    }
    
    public void setDishCount(int dishCount) {
        this.dishCount = dishCount;
    }
}
