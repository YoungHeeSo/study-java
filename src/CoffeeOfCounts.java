import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class CoffeeOfCounts extends JDialog implements ActionListener{
	String pl_img = "C:\\Users\\202216008\\Pictures\\javaimg\\plus_1.png";
	String min_img = "C:\\Users\\202216008\\Pictures\\javaimg\\minus_1.png";
	
	CoffeeOrdering cofe_order;
	orderingpay order_pay;	
	
	JButton pl_btn, min_btn, put_btn, close_btn;
	static JButton order_ok_btn;
	
	static JTextArea menu_nm, menu_price;
	static JTextArea cofe_order_nm;
	JTextArea nu = new JTextArea("");
	
	ImageIcon menu_img, plus, minus;
	
	JPanel menu_data;
	
	JLabel img_data;
	
	static JTextField menu_count;
	
	JFrame owner;
	
	static String[] title= {"메뉴","개수","가격"};
	static Object[][] row= new Object[0][2];
	static DefaultTableModel mo = new DefaultTableModel(row, title);
//	static DefaultTableModel two = new DefaultTableModel(row, title);	
	static JTable ta = new JTable(mo);
	
	static int count, price, sum_price; //개수*가격	
	static int noften = 0; //장바구니
	
	static String[] put_list = new String[3];
	
	public CoffeeOfCounts(JFrame owner, String cofe_nm, String img, String cofe_price, JButton ok_btn) {
		super(owner, cofe_nm, true);
		//this.setLayout(null);
		this.setBounds(100, 100, 800, 800);
	
		
		menu_img = new ImageIcon(img);
		plus = new ImageIcon(pl_img);
		minus = new ImageIcon(min_img);
				
		menu_data = new JPanel();
		menu_data.setBackground(new Color(0xe6d4c3));
		menu_data.setLayout(null);
		menu_data.setBounds(0, 0, 800, 800);	
		
		img_data = new JLabel(menu_img);
		img_data.setBounds(50, 50, 300, 300);
		
		cofe_order_nm = new JTextArea(cofe_nm);
		
		menu_nm = new JTextArea(cofe_nm);
		menu_nm.setBounds(img_data.getX()+150, img_data.getY()+300, 100, 30);
		menu_price = new JTextArea(cofe_price);
		menu_price.setBounds(menu_nm.getX(), menu_nm.getY()+30, 100, 30);
		
		menu_count = new JTextField("1", 2);
		menu_count.setBounds(img_data.getX()+150, img_data.getY()+370, 100, 30);		
		menu_count.setEditable(false);
		
		order_ok_btn = new JButton();
		order_ok_btn = ok_btn;	
		
		pl_btn = new JButton(plus);
		pl_btn.setBounds(menu_count.getX()-100, menu_count.getY(), 70, 80);
		
		min_btn = new JButton(minus);
		min_btn.setBounds(menu_count.getX()+120, menu_count.getY(), 70, 80);
		
		put_btn = new JButton(menu_price.getText()+"원 담기");
		put_btn.setBounds(menu_count.getX(), menu_count.getY()+150, 150, 80);
		
		close_btn = new JButton("닫기"); 
		close_btn.setBounds(put_btn.getX(), put_btn.getY()+100, 100, 50);
		
		pl_btn.addActionListener(this);
		min_btn.addActionListener(this);
		close_btn.addActionListener(this);
		put_btn.addActionListener(this);

		menu_nm.setEditable(false);
		menu_price.setEditable(false);
		
		img_data.setIcon(menu_img);			
		menu_data.add(img_data);
		menu_data.add(menu_nm);
		menu_data.add(menu_price);
		menu_data.add(pl_btn);
		menu_data.add(menu_count);
		menu_data.add(min_btn);		
		menu_data.add(put_btn);
		menu_data.add(close_btn);
		
		this.add(img_data);
		this.add(menu_data);		
		this.setVisible(true);
	
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub			
			count = Integer.parseInt(menu_count.getText()); //개수
			price  = Integer.parseInt(menu_price.getText()); //하나가격
			if (e.getSource() == pl_btn) {
				++count;
			}
			
			else if(e.getSource() == min_btn) {
				--count;
				if(count < 1 ){
					count=1;
				}
			}
			
			if (e.getSource() == close_btn) { //이거 누르면 아무것도 메뉴 리스트에 넣지 않고 닫아지는거 
				count=0;				
				menu_nm.setText("아꺼져");				
				this.setVisible(false);				
			}

			else if(e.getSource() == put_btn) { //cof_order에 장바구니 버튼 활성화 시키고 메뉴 추가하기				
				put_list[0] = menu_nm.getText();
				put_list[1] = count+"";
				put_list[2] = count*price+"";
				
				mo.addRow(put_list);
				
				noften++;
				cofe_order.cofe_put.setEnabled(true);			

				System.out.println(noften);
				System.out.println(put_list); //총 계샨
				setVisible(false);
				order_ok_btn.setEnabled(false);	
			}			
			
			sum_price = count*price;
			menu_count.setText(count+"");
			put_btn.setText(sum_price+"원 담기");
			System.out.println("here see > "+Arrays.deepToString(put_list));
					
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CoffeeOfCounts(null, null, null, null, null);
	}
	
}


//put_list[0] = menu_nm.getText();
//put_list[1] = menu_count.getText();

//else if(){
//cofe_order.coffee_btns[9].setEnabled(false);
//}

//if(!".equals(menu_nm.getText())) {
//order_ok_btn.setEnabled(true);
//}

//if(!cofe_order_nm.getText().equals(menu_nm.getText())) {
//order_ok_btn.setEnabled(true);
//}

//if(cofe_order.cofe_menu[1].equals(menu_nm.getText())) { //오류나면액션이벤트로 데리고가기
////if(menu_nm.equals(menu_nm.getText())) {
////		if(cofe_menu[0].equals(cofe_counts.ta.)) {
//	cofe_order.coffee_btns[1].setEnabled(false);		//2개째부터 안됨	값들어온순간부터 false가 되도록 바꾸기
//}
//orderingpay.menu_list[0] = menu_nm.getText();
//orderingpay.menu_list[1] = menu_count.getText();
//orderingpay.menu_list[2] = sum_price+"";
//System.out.println(menu_nm.getText()+ menu_count.getText()+ sum_price+"");


//if(count == 1) {
//	//cofe_order.cofe_menu[1]="매진!";
//	//order_pay = new orderingpay(menu_nm.getText(), menu_count.getText(), sum_price+"");
//	System.out.println(menu_nm.getText()+ menu_count.getText()+ sum_price+"");
//	
////if(menu_nm.getText() == "")
//	//order_pay = new orderingpay(menu_nm.getText(), menu_count.getText(), sum_price+"");
//
////order_pay.menu_list[0] = menu_nm.getText();
////order_pay.menu_list[1] = menu_count.getText();
////order_pay.menu_list[2] = 	;
////order_pay=new orderingpay(order_pay.menu_list[0], order_pay.menu_list[1], order_pay.menu_list[2]);
// // 담기 버튼을 누르면 메뉴리스에 추가되고, 수량 고르는 창은 사라지는 용도