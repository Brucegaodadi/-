package com.gao.dao;

import com.gao.bean.User;
import com.gao.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class LoginDao {
    public User getUserByUsernameAndPwd(String username, String password) throws SQLException {
        //创建QueryRunner
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        //编写Sql
        String sql = "select * from day09 where username=? and password =?";
        //执行Sql
        User user = qr.query(sql, new BeanHandler<User>(User.class), username,password);
        /*User user = qr.query(sql, new BeanHandler<User>(User.class), username, password);*/
        return user;
    }
}
