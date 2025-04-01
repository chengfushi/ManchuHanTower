package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author Cheng fu
 * @Date 2025/4/1 16:38
 */
public class JDBCUtilsByDruid {
    private static DataSource dataSource;
    
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\main\\resources\\mysql.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    //编写静态方法，连接数据库
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    //关闭数据库连接
    public static void close(ResultSet resultSet, Statement statement,Connection connection){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
