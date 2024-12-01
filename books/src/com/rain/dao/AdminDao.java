package com.rain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rain.bean.AdminBean;
import com.rain.bean.BookBean;
import com.rain.util.C3P0Utils;

import javax.sql.DataSource;

public class AdminDao
{
	private DataSource dataSource;
	public AdminDao(){this.dataSource=C3P0Utils.getDataSource();}
	public boolean Login_verify(String username, String password) //验证登录
	{

		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "select * from admin where username='" + username + " 'and password='" + password + "'";
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next())
			{
				return true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public void Register(String username, String password, String name, String email, String phone, int lend_num, int max_num) //注册账号
	{

		String sql = "insert into admin(status,username,password,name,email,phone,lend_num,max_num) values(?,?,?,?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, 1);
			stm.setString(2, username);
			stm.setString(3, password);
			stm.setString(4, name);
			stm.setString(5, email);
			stm.setString(6, phone);
			stm.setInt(7, lend_num);
			stm.setInt(8, max_num);
			rs = stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void Register2(String username, String password, String name, String email, String phone) //添加管理员账号
	{

		String sql = "insert into admin(status,username,password,name,email,phone,lend_num,max_num) values(?,?,?,?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, 2);
			stm.setString(2, username);
			stm.setString(3, password);
			stm.setString(4, name);
			stm.setString(5, email);
			stm.setString(6, phone);
			stm.setInt(7,0);
			stm.setInt(8,0);
			rs = stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public AdminBean getAdminInfo(String username, String password) //通过账号密码查读者信息
	{
		AdminBean adminbean = new AdminBean();
		String sql = "select * from admin where username= '"+username+"' and password= '"+password+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next())
			{
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return adminbean;
	}
	public ArrayList<AdminBean> get_ListInfo() //查所有非管理用户
	{
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		String sql = "select * from admin where status=1";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				AdminBean adminbean = new AdminBean();
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
				tag_Array.add(adminbean);
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}

	public ArrayList<AdminBean> get_ListInfo2() //查所有管理用户
	{
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();

		String sql = "select * from admin where status=2";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				AdminBean adminbean = new AdminBean();
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				tag_Array.add(adminbean);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
	public AdminBean get_admin_admin() //查最高管理员
{

	String sql = "select * from admin where aid=10000";
	PreparedStatement stm = null;
	ResultSet rs = null;
	AdminBean adminbean = new AdminBean();
	try
	{
		Connection conn = dataSource.getConnection();
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		while(rs.next()) {
			adminbean.setAid(rs.getInt("aid"));
			adminbean.setUsername(rs.getString("username"));
			adminbean.setName(rs.getString("name"));
			adminbean.setPassword(rs.getString("password"));
			adminbean.setEmail(rs.getString("email"));
			adminbean.setPhone(rs.getString("phone"));
			adminbean.setTimes(rs.getInt("times"));
			adminbean.setStatus(rs.getInt("status"));
			adminbean.setLend_num(rs.getInt("lend_num"));
			adminbean.setMax_num(rs.getInt("max_num"));
		}
	}
	catch (SQLException e)
	{
		e.printStackTrace();
	}
	return adminbean;
}
	public ArrayList<AdminBean> get_ListInfo3() //查所有读者并按次数排名
	{
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		String sql = "select * from admin where status=1 order by times desc";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				AdminBean adminbean = new AdminBean();
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
				tag_Array.add(adminbean);
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
	public AdminBean get_AidInfo(int aid) //根据id查找用户信息
	{
		AdminBean adminbean = new AdminBean();

		String sql = "select * from admin where aid=" + aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return adminbean;
	}
	public AdminBean get_AidInfo2(String aid) //同上传参不同
	{
		AdminBean adminbean = new AdminBean();
		String sql = "select * from admin where aid=" + aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next())
			{
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return adminbean;
	}
	public void updateUser(int aid, String username, String password, String name, String email, String phone, int lend_num, int max_num) //更新读者信息
	{

		String sql = "update admin set username=?,name=?,email=?,phone=?,password=?,lend_num=?,max_num=? where aid=?";
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, name);
			stm.setString(3, email);
			stm.setString(4, phone);
			stm.setString(5, password);
			stm.setInt(6, lend_num);
			stm.setInt(7, max_num);
			stm.setInt(8, aid);
			stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void updateAdmin(int aid, String username, String password, String name, String email, String phone) //更新管理员信息
	{
		String sql = "update admin set username=?,name=?,email=?,phone=?,password=? where aid=?";
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, name);
			stm.setString(3, email);
			stm.setString(4, phone);
			stm.setString(5, password);
			stm.setInt(6, aid);
			stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void deleteUser(int aid)//删除账号
	{
		String sql = "delete from admin where aid=?";
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, aid);
			stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void deleteAdmin(int aid) //删除管理员
	{
		String sql = "delete from admin where aid=?";
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, aid);
			stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<AdminBean> getLikeList(String name) //搜索用户
	{
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		String sql = "select * from admin where name like '%" + name + "%' or username like '%" + name + "%' or aid like '%" + name + "%'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				AdminBean tag = new AdminBean();
				tag.setAid(rs.getInt("aid"));
				tag.setStatus(rs.getInt("status"));
				tag.setUsername(rs.getString("username"));
				tag.setName(rs.getString("name"));
				tag.setPassword(rs.getString("password"));
				tag.setEmail(rs.getString("email"));
				tag.setPhone(rs.getString("phone"));
				tag.setTimes(rs.getInt("times"));
				tag.setLend_num(rs.getInt("lend_num"));
				tag.setMax_num(rs.getInt("max_num"));
				tag_Array.add(tag);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
}
