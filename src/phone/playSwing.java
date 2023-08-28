package phone;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class playSwing extends JFrame implements ActionListener{
	//JFrame 배치방식 = BorderLayout 방식(동/서/남/북/센터, 기본이 센터)
	JTextField tf_name, tf_phone, tf_email, tf_age;
	JTable jt;
	DefaultTableModel dtm;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	
	public playSwing() {
		this.setTitle("자바네");
		this.setSize(500, 800); //창사이즈
		this.setLocation(1420, 600);
		this.setLayout(null); //맨윗줄, 중앙에 차례로 배치됨 
		JPanel p1 = new JPanel(); //JPanel 배치방식 = FlowLayout
		JPanel p2 = new JPanel();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //메모리완전히닫아서메모리에쌓이지않게해줌
		String[] title= {"이름", "번호", "메일","나이"};
		Object[][] row = new Object[0][4];
		dtm = new DefaultTableModel(row, title);
		jt = new JTable(dtm);
		JScrollPane p3 = new JScrollPane(jt);
		jt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = jt.getSelectedRow();
				tf_name.setText(jt.getValueAt(row, 0).toString());
				tf_phone.setText(jt.getValueAt(row, 1).toString());
				
				tf_email.setText(jt.getValueAt(row, 2).toString());
				System.out.println(jt.getValueAt(row, 3));
				tf_age.setText((String)jt.getValueAt(row, 3).toString());
				
			}	
		
				}
				);
		
		JButton bt1 = new JButton("전화번호부");
		JButton bt2 = new JButton("검색");
		JButton bt3 = new JButton("수정");
		JButton bt4 = new JButton("삭제");
		JButton bt5 = new JButton("추가");
		JButton bt6 = new JButton("초기화");
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		bt5.addActionListener(this);
		bt6.addActionListener(this);
		
		JLabel name = new JLabel("이름: ");
		JLabel phone = new JLabel("번호: ");
		JLabel email = new JLabel("메일: ");
		JLabel age = new JLabel("나이: ");
		
		tf_name = new JTextField(20); tf_phone = new JTextField(20);
		tf_email = new JTextField(20); tf_age = new JTextField(20);
		
		p1.add(name); p1.add(tf_name); 
		p1.add(phone); p1.add(tf_phone);
		p1.add(email); p1.add(tf_email); 
		p1.add(age); p1.add(tf_age);
		
		p2.add(bt1);
		p2.add(bt2);
		p2.add(bt3);
		p2.add(bt4);
		p2.add(bt5);
		p2.add(bt6);
		
		p1.setBounds(10, 10, 250, 110);
		p2.setBounds(10, 130, 460, 40);
		p3.setBounds(10, 180, 460, 670);
		p1.setBackground(Color.pink);
		p2.setBackground(Color.blue);
		p3.setBackground(Color.white);
		this.add(p1);this.add(p2);this.add(p3);
		
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new playSwing(); //프레임 객체 생성
		try{
			UIManager.setLookAndFeel ("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//LookAndFeel Windows 스타일 적용
			//SwingUtilities.updateComponentTreeUI(this) ;
		}catch(Exception e){
			e.getMessage();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("전화번호부")){
			display(0);
			
		}
		else if(cmd.equals("검색")) {
			display(1);

		}
		
		else if(cmd.equals("수정")) {
			update();
			display(0);
		}
		
		else if(cmd.equals("삭제")) {
			delete();
			display(0);
		}
		
		else if(cmd.equals("추가")) {
			insert();
			display(0);
		}
		
		else if(cmd.equals("초기화")) {
			tf_name.setText("");
			tf_phone.setText("");
			tf_email.setText("");
			tf_age.setText(null);
		}
	}
	
	public ResultSet select(int kind) {
		con = makeConnection();
		if(con != null ) {
			String sql;
			try {
				if(kind == 0) { //전화번호부 리스팅
					sql = "select * from person";
					ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				}
				else {
					sql = "select * from person where name = ?";
					ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ps.setString(1, tf_name.getText());
				}
				rs = ps.executeQuery(); //실행한 쿼리에 변화를 일으킬때 //ps.executeQuery select실행할때
				System.out.println(rs);
				if(rs.next()) {
					rs.beforeFirst();
				}
				else {
					if(rs.next()) {
						JOptionPane.showMessageDialog(this, "일치하는 이름이 검색되지 않았습니다.");
						System.out.println("검색된 결과 없어");
						sql="select * from person";
						ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						rs = ps.executeQuery();
					}
					
				}
				
			}
				catch (SQLException e) {
				System.out.println("ps 객체생성 오류");
				e.printStackTrace();
			}
			
		}
		
		return rs;
	}
	
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
	}
	
	public void delete() {
		con=makeConnection();
		if(con != null) {
			try {
				String sql = "delete from person where phone = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, tf_phone.getText());
				ps.executeUpdate();
		
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void insert() {
		con=makeConnection();
		if(con != null) {
			String sql;
			if(tf_age.getText().equals(""))
				sql = "insert into person(name, phone, email) values(?,?,?,?)";
			else
				sql = "insert into person values(?,?,?,?)";
			int x=0;
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, tf_name.getText());
				ps.setString(2, tf_phone.getText());
				ps.setString(3, tf_email.getText());
				if(!tf_age.getText().equals(""))
					ps.setInt(4, Integer.parseInt(tf_age.getText()));
				x = ps.executeUpdate();
			}
			catch(SQLException ie) {
				System.out.println("추가실패...");
				if(x==0)
					JOptionPane.showMessageDialog(this, "전화번호가 중복되었거나 필수 입력 사항이 누락되었는지 확인하세요");
			}
		}
	}
	
	public void display(int kind) {
		rs = select(kind);
		
		try {
			String info[]=new String[4];
			dtm.setRowCount(0);
			while(rs.next()) {
				info[0] = rs.getString(1);
				info[1] = rs.getString(2);
				info[2] = rs.getString(3);
				info[3] = rs.getString(4);
				dtm.addRow(info);
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public Connection makeConnection() {
		String url = "jdbc:mysql://localhost:3306/myapp?serverTimezone=Asia/Seoul";
		String id = "root";
		String pass = "1234";
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("데이터베이스 연결중...");
			con=DriverManager.getConnection(url, id, pass);
			System.out.println("데이터베이스 연결성공!");
		}
		
		catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버를 찾지 못했습니다.");
		}
		
		catch(SQLException e) {
			System.out.println("데이터베이스 연결실패...");
			System.out.println(e.getMessage());
		}
		
		return con;
		
		}
}

