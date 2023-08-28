package day;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.Statement;

public class DBopen {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/*
	public void update() {
		con=makeConnection();
		if(con != null) {
			try {
				String sql;
					sql="update person set name =?, email = ?, age=? where phone = ?";
	
					ps=con.prepareStatement(sql);
					ps.setString(1,tf_name.getText());
					ps.setString(2, tf_email.getText());
					if(tf_age.getText().equals(""))
						ps.setString(3, null);
					else
						ps.setInt(3, Integer.parseInt(tf_age.getText()));
					ps.setString(4, tf_phone.getText());
					ps.executeUpdate();
				}
				
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
	}*/
	
	public void delete(int id) {
		con=makeConnection();
		if(con != null) {
			try {
				String sql = "delete from memo where id = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ps.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void insert(String dd, String msg) {
		con=makeConnection();
		if(con != null) {
			String sql;
			sql = "insert into memo values(null, ?, ?)";
			
			int x=0;
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, dd);
				ps.setString(2, msg);
				x = ps.executeUpdate();
				if(x==1)
					System.out.println("추가성공!!");
			}
			catch(SQLException ie) {
				System.out.println("추가실패...");
				ie.getMessage();
				
			}
		}
	}
	
	public ResultSet select() {
		con=makeConnection();
		if(con != null) {
			String sql;

			try {				
				sql = "select distinct date_num from memo";
				ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = ps.executeQuery();					
			} catch (SQLException e) {
				System.out.println("ps 객체생성 오류");
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	


	
	public ResultSet select(String dd) {
		con = makeConnection();
		if(con != null ) {
			String sql;
			try{
				sql = "select * from memo where date_num = ?";
				ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ps.setString(1, dd);
				rs = ps.executeQuery(); //실행한 쿼리에 변화를 일으킬때 //ps.executeQuery select실행할때
			}catch (SQLException e) {
				System.out.println("ps 객체생성 오류");
				e.printStackTrace();
			}
			
		}
		
		return rs;
	}
	
	
	public Connection makeConnection() {
		String url = "jdbc:mysql://localhost:3306/myApp?serverTimezone=Asia/Seoul";
		String id = "root";
		String pass = "1234";
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("데이터베이스 연결중 ...");
			con = DriverManager.getConnection(url, id, pass);
			System.out.println("데이터베이스 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버를 찾지 못했습니다...");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");
            System.out.println(e.getMessage());
		}
		return con;
	}
}

