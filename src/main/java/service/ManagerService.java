package service;

import dao.ManagerDao;
import daomain.Manager;

/**
 * 这个类是管理员服务类，为管理员提供服务，主要提供了管理员的登录、修改密码、添加菜品、删除菜品、修改菜品等功能
 * @Author Cheng fu
 * @Date 2025/4/1 19:09
 */
public class ManagerService {
    private ManagerDao managerDao = new ManagerDao();
    private MenuService menuService = new MenuService();
    
    public boolean login(String username,String password) {
        String sql = "select * from manager where username = ? and password = ?";
        return managerDao.querySingle(sql, Manager.class,username,password) != null;
    }
    
    public boolean changePassword(String username,String oldPassword,String newPassword) {
        String sql = "update manager set password = ? where username = ? and password = ?";
        if (!login(username,oldPassword)) {
            return false;
        }
        return managerDao.update(sql,newPassword,username,oldPassword) > 0;
    }
    
    //添加菜品
    public boolean addMenu(String dishName,double price) {
        return menuService.addDish(dishName,price);
    }
    
    //删除菜品
    public boolean deleteMenu(String dishName) {
        return menuService.deleteDish(dishName);
    }
    
    //修改菜品
    public boolean updateMenu(String dishName,double price) {
        return menuService.updateDish(dishName,price);
    }
}
