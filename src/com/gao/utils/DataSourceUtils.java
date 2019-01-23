package com.gao.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;



public class DataSourceUtils {
	private static DataSource ds=new ComboPooledDataSource();
	
	//获取连接池
	public static DataSource getDataSource(){
		return ds;
	}
	
	//获取连接
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
}
