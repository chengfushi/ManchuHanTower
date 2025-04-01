package service;

import dao.UserDao;

/**
 * @Author Cheng fu
 * @Date 2025/4/1 18:58
 */
public class UserService {
    
    //创建UserDao对象用于操作数据库
    private UserDao userDao = new UserDao();
    
    /**
     *@Description 实现登录功能，第一次登录自动注册
     * @param password
     * @param username
     *@return
     *@date 2025/4/1 19:01
     *@auther Cheng fu
     */
    
    public boolean login(String username,String password) {
        String sql = "select count(*) from user where username = ?";
        //查询是否有该用户
        Object o = userDao.queryScalar(sql, username);
        if (o == null || o.equals(0)) {
            //自动注册
            sql = "insert into user values(?,?)";
            userDao.update(sql, username, password);
            return true;
        }
        sql = "select count(*) from user where username = ? and password = ?";
        Object o1 = userDao.queryScalar(sql, username, password);
        if (o1 == null || o1.equals(0)) {
            return false;
        }
        return true;
    }
    
    
    public boolean changePassword(String username,String oldPassword,String newPassword) {
        if (!login(username,oldPassword)) {
            return false;
        }
        String sql = "update user set password = ? where username = ?";
        return userDao.update(sql,newPassword,username) > 0;
    }
    
    
}
