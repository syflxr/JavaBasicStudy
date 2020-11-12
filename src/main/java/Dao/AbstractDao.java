package Dao;

import Annotation.Table;
import javafx.scene.control.Tab;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: shenyafeng
 * @Date: 2020/11/9 10:09
 * @Description:
 */
public class AbstractDao<T> {
    protected String URL = "jdbc:mysql://localhost:3306/memory?useSSL=false&characterEncoding=utf8";
    protected String name = "root";
    protected String password = "chihuo636395";
    protected Connection connection;

    private String tableName;
    private Class<T> clazz;

    {
        try {
            connection = DriverManager.getConnection(URL, name, password);
        } catch (SQLException throwables) {
            System.out.println("Abstract Dao" + "数据库初始化失败");
        }
    }

    public AbstractDao() {
        Type genType = this.getClass().getGenericSuperclass();//返回父类包括泛型参数, this.getClass().getSuperclass()
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        this.clazz = (Class<T>) params[0];//返回第一个泛型
        getTableName();
    }

    protected String getTableName() {
        if (StringUtils.isEmpty(tableName)) {
            Table t = clazz.getAnnotation(Table.class);
            if (t != null) {
                tableName=t.name();
            } else {
                System.out.println("获取表名称失败"+clazz);
            }
        }
        return tableName;
    }

    protected void insert(T t){

    }

    protected void delete(T t){

    }

    private void update(T t){

    }


}
