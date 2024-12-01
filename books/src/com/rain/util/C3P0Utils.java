package com.rain.util;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.*;
public class C3P0Utils {
	private static DataSource dataSource;
	static
	{
		try
		{
			dataSource = new ComboPooledDataSource();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static DataSource getDataSource()
	{
		return dataSource;
	}
}
