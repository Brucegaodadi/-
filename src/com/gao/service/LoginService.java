package com.gao.service;

import com.gao.bean.User;
import com.gao.dao.LoginDao;

import java.sql.SQLException;

public class LoginService {
    public User getUserByUsernameAndPwd(String username, String password) throws SQLException {
        LoginDao ld = new LoginDao();
        return ld.getUserByUsernameAndPwd(username,password);
    }
}
