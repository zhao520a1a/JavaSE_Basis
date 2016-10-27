package com.xin.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;


public class JDBC {

	public static void main(String[] args) {

//		add();
//		update();
//		delete();
//		query();
		
		procudure();  //调用存储过程   ----"call procu_name(?,?,?,?)";
		function();   //调用函数     ----"{? = call fun_name(?,?,?)}";
		
	}

	private static void procudure() {
		Connection con = null ;
		CallableStatement cs = null;
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://192.168.1.111/bz?userUnicode=true?characterEncoding=utf-8", "root", "root");	
			
			//调用函数ordertotal，它是用来计算税费的；
			String sql = "call ordertotal(?,?,?,?)";
			cs = con.prepareCall(sql);
			cs.setDouble(1, 0.6);
			cs.setInt(2, 20005);
			cs.setBoolean(3, true);
			cs.registerOutParameter(4, java.sql.Types.INTEGER);//注册输出参数类型
			
			cs.execute();
			
			System.out.println(cs.getDouble(4));
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(cs != null) {
					cs.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void function() {
		Connection con = null ;
		CallableStatement cs = null;
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://192.168.1.106/example?userUnicode=true?characterEncoding=utf-8", "root", "root");	
			
			//调用函数ordertotal，它是用来计算税费的；
			String sql = "{? = call ordertotal(?,?,?)}";
			cs = con.prepareCall(sql);
			cs.registerOutParameter(1, java.sql.Types.INTEGER);//注册输出参数类型
			cs.setDouble(2, 0.6);
			cs.setInt(3, 20005);
			cs.setBoolean(4, true);
			
			cs.execute();
			
			System.out.println(cs.getDouble(1));
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(cs != null) {
					cs.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
  
	private static void query() {
		Connection con = null ;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.1.111/bz?userUnicode=true?characterEncoding=utf-8", "root", "root");	
			
			String sql = "select * from tax_orders where total >  ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, 100);
			rs = ps.executeQuery();
			while(rs.next()) {
				int order_num = rs.getInt("order_num");
			    int total = rs.getInt("total");
				System.out.println(order_num + "|" + total );
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) 
					rs.close();
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void delete() {
		Connection con = null ;
		PreparedStatement ps = null;
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.1.111/bz?userUnicode=true?characterEncoding=utf-8", "root", "root");	
			
			String sql = "delete from tax_orders where order_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, 123);
			int flag = ps.executeUpdate();
			
			System.out.print(flag > 0 ? "执行成功" : "执行失败");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void update() {
		Connection con = null ;
		PreparedStatement ps = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.1.111/bz?userUnicode=true&characterEncoding=utf-8", "root", "root");
			
			String sql = "update tax_orders set total = ? where order_num =?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, 789);
			ps.setInt(2, 123);
			
			int flag = ps.executeUpdate();
			
			System.out.print(flag > 0 ? "执行成功" : "执行失败");
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	private static void add() {
		Connection con = null;
		PreparedStatement ps = null;
		try {
		    //把mysql的驱动加载到内存
			Class.forName("org.gjt.mm.mysql.Driver"); //com.mysql.jdbc.Driver
			//建立mysql数据库的连接
			con = DriverManager.getConnection("jdbc:mysql://192.168.1.111/bz?useUnicode=true&characterEncoding=utf-8", "root", "root");
			
			String sql = "insert into tax_orders values(?, ?)";
			//为sql语句赋参数
			ps = con.prepareStatement(sql);
			ps.setInt(1, 123);
			ps.setInt(2, 456);
			//执行语句
			int flag = ps.executeUpdate();
			if(flag > 0) {
				System.out.println("执行成功");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}
}
