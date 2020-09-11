package utils;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBUtils {
    // 数据库连接地址
    public static String URL;
    // 用户名
    public static String USERNAME;
    // 密码
    public static String PASSWORD;
    // mysql的驱动类
    public static String DRIVER;

    private static Properties pr = new Properties();

    private DBUtils() {
    }

    // 使用静态块加载驱动程序
    static {
        try {
            pr.load(DBUtils.class.getClassLoader().getResourceAsStream("resource/database.properties"));
            URL = pr.getProperty("jdbc.url");
            USERNAME = pr.getProperty("jdbc.username");
            PASSWORD = pr.getProperty("jdbc.password");
            DRIVER = pr.getProperty("jdbc.driver");
            Class.forName(DRIVER);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            e.printStackTrace();
        }

    }

    // 定义一个获取数据库连接的方法
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取连接失败");
        }
        return conn;
    }

    // 关闭数据库连接
    public static void close(ResultSet rs, Statement stat, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (stat != null)
                stat.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
