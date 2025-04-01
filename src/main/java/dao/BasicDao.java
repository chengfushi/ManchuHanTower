package dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtilsByDruid;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @Author Cheng fu
 * @Date 2025/4/1 16:16
 * @Version 1.0
 * @Description:开发基类Dao，用于数据库的最基本功能
 */
public class BasicDao <T>{
    private QueryRunner qr = new QueryRunner();
    
    /**
     *@Description 开发通用的dml方法，针对任意的表
     * @param sql
     * @param parameters
     *@return int
     *@date 2025/4/1 18:27
     *@auther Cheng fu
     */
    public int update(String sql,Object... parameters) {
        //创建connect对象
        Connection connection = null;
        
        //通过编写好的JDBC工具类获取连接
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.update(connection,sql,parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }
    
    /**
     *@Description 创建通用的查询语句
     * @param sql
     * @param clazz
     * @param parameters
     *@return java.util.List<T>
     *@date 2025/4/1 18:31
     *@auther Cheng fu
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {
        //创建connection连接
        Connection connection = null;
        
        //通过JDBC工具类获取连接
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection,sql,new BeanListHandler<T>(clazz),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }
    
    /**
     *@Description 查询单行结果的通用方法
     * @param sql
     * @param clazz
     * @param parameters
     *@return T
     *@date 2025/4/1 18:37
     *@auther Cheng fu
     */
    public T querySingle(String sql,Class<T> clazz,Object... parameters){
        Connection connection = null;
        
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection,sql,new BeanHandler<T>(clazz),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }
    
    /**
     *@Description 创建单列的查询方法
     * @param sql
     * @param parameters
     *@return java.lang.Object
     *@date 2025/4/1 18:41
     *@auther Cheng fu
     */
    public Object queryScalar(String sql,Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection,sql,new ScalarHandler<>(),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }
}
