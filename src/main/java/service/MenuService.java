package service;

import dao.MenuDao;
import daomain.Menu;

/**
 * 这是一个菜单服务类，主要能够添加菜品，删除菜品，修改菜品，查询菜品，显示菜品
 * @Author Cheng fu
 * @Date 2025/4/1 19:09
 */
public class MenuService {
    private MenuDao menuDao = new MenuDao();
    
    public boolean addDish(String disName,double price) {
        String sql = "insert into menu(dishName,price) values(?,?)";
        return menuDao.update(sql,disName,price) > 0;
    }
    public boolean deleteDish(String disName) {
        String sql = "delete from menu where dishName = ?";
        return menuDao.update(sql,disName) > 0;
    }
    public boolean updateDish(String disName,double price) {
        String sql = "update menu set price = ? where dishName = ?";
        return menuDao.update(sql,price,disName) > 0;
    }
    public boolean queryDish(String disName) {
        String sql = "select * from menu where dishName = ?";
        return menuDao.querySingle(sql,Menu.class,disName) != null;
    }
    public void showDish() {
        String sql = "select * from menu";
        menuDao.queryMulti(sql, Menu.class).forEach(System.out::println);
    }
}
