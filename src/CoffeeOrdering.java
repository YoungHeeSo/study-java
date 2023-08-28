import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CoffeeOrdering extends JFrame implements ActionListener{
	String cof_ameri = "C:\\Users\\202216008\\Pictures\\javaimg\\아메리카노_btn.jpg";
	String cof_pectea = "C:\\Users\\202216008\\Pictures\\javaimg\\아이스티_btn.jpg";
	String cof_lat = "C:\\Users\\202216008\\Pictures\\javaimg\\라떼_btn.jpg";
	String cof_match_lat = "C:\\Users\\202216008\\Pictures\\javaimg\\말차라떼_btn.jpg";
	String cof_apog = "C:\\Users\\202216008\\Pictures\\javaimg\\아포가토_btn.jpg";
	String cof_pong = "C:\\Users\\202216008\\Pictures\\javaimg\\퐁크러쉬_btn.png";
	String back = "C:\\Users\\202216008\\Pictures\\javaimg\\back.jpg";
	
	String cof_logo = "C:\\Users\\202216008\\Pictures\\javaimg\\COFFEE_LOGO.png";
	
	CoffeeOfCounts cofe_counts;
	orderingpay order_pay;
	
	static String[] cofe_menu = {"아메리카노", "복숭아 아이스티", "라떼", "밀차라떼", "아포가토","퐁크러쉬"};
	String[] cofe_price = {"1500", "1500", "2500", "2500", "3000", "3000"};
	
	JTextArea[] coffee_kinds = new JTextArea[cofe_menu.length];
	JTextArea[] coffee_prices = new JTextArea[cofe_menu.length];
	ImageIcon[] btn_img = new ImageIcon[cofe_menu.length];
	
	static JButton[] coffee_btns = new JButton[cofe_menu.length];	
	static JButton cofe_put;
	
	ImageIcon cofe_icon;
	JLabel cofe_logo;
	
	int check;
		
	public CoffeeOrdering(){		
		//this.setLayout(null);
		this.setBounds(100, 100, 800, 800); //x:100, y:100 위치에 width=800, height=800 사이즈 창 
		this.setTitle("소영이의 카페커피 주문하기");
		
		cofe_icon = new ImageIcon(cof_logo);
		cofe_logo = new JLabel(cofe_icon);
		cofe_logo.setBounds(510, 610, 320, 120);
		
		btn_img[0] = new ImageIcon("image\\javaimg\\아메리카노_btn.jpg");		
		btn_img[1] = new ImageIcon("image\\javaimg\\아이스티_btn.jpg");
		btn_img[2] = new ImageIcon("image\\javaimg\\라떼_btn.jpg");
		btn_img[3] = new ImageIcon("image\\javaimg\\말차라떼_btn.jpg");
		btn_img[4] = new ImageIcon("image\\javaimg\\아포가토_btn.jpg");
		btn_img[5] = new ImageIcon("image\\javaimg\\퐁크러쉬_btn.png");
		
		JPanel coffee_box1 = new JPanel(); 
		coffee_box1.setLayout(null);	
		coffee_box1.setBackground(new Color(0xe6d4c3));		
		
		//버튼
		coffee_btns[0] = new JButton(btn_img[0]); //아메리카노		
		coffee_btns[0].setBounds(10, 10, 200, 200);	
		
		coffee_btns[1] = new JButton(btn_img[1]); //복숭아아이스티		
		coffee_btns[1].setBounds(coffee_btns[0].getX()+210, coffee_btns[0].getY(), 200, 200);	
		
		coffee_btns[2] = new JButton(btn_img[2]); //라떼
		coffee_btns[2].setBounds(coffee_btns[1].getX()+210, coffee_btns[0].getY(), 200, 200);
		
		coffee_btns[3] = new JButton(btn_img[3]); //말차라떼
		coffee_btns[3].setBounds(coffee_btns[0].getX(), coffee_btns[0].getY()+250, 200, 200);	
		
		coffee_btns[4] = new JButton(btn_img[4]); //아포가토
		coffee_btns[4].setBounds(coffee_btns[1].getX(), coffee_btns[3].getY(), 200, 200);
		
		coffee_btns[5] = new JButton(btn_img[5]); //퐁크러쉬
		coffee_btns[5].setBounds(coffee_btns[2].getX(), coffee_btns[3].getY(), 200, 200);
		
		cofe_put = new JButton("장바구니 보기"); //계산
		cofe_put.setBounds(coffee_btns[0].getX(), coffee_btns[3].getY()+250, 200, 200);
		cofe_put.setEnabled(false);		
		
		coffee_btns[0].addActionListener(this);
		coffee_btns[1].addActionListener(this);
		coffee_btns[2].addActionListener(this);	
		coffee_btns[3].addActionListener(this);	
		coffee_btns[4].addActionListener(this);	
		coffee_btns[5].addActionListener(this);	
		cofe_put.addActionListener(this);
		
		coffee_kinds[0]=new JTextArea(cofe_menu[0]);
		coffee_prices[0]=new JTextArea(cofe_price[0]);
		coffee_kinds[0].setBounds(coffee_btns[0].getX()+50, coffee_btns[0].getY()+200, 200, 200);
		coffee_prices[0].setBounds(coffee_btns[0].getX()+60, coffee_btns[0].getY()+220, 100, 100);
		coffee_kinds[0].setBackground(new Color(0,0,0,0));
		coffee_prices[0].setBackground(new Color(0,0,0,0));
		
		coffee_kinds[1]=new JTextArea(cofe_menu[1]);
		coffee_prices[1]=new JTextArea(cofe_price[1]);
		coffee_kinds[1].setBounds(coffee_btns[1].getX()+50, coffee_btns[1].getY()+200, 200, 200);
		coffee_prices[1].setBounds(coffee_btns[1].getX()+60, coffee_btns[1].getY()+220, 100, 100);
		coffee_kinds[1].setBackground(new Color(0,0,0,0));
		coffee_prices[1].setBackground(new Color(0,0,0,0));
		
		coffee_kinds[2]=new JTextArea(cofe_menu[2]);
		coffee_prices[2]=new JTextArea(cofe_price[2]);
		coffee_kinds[2].setBounds(coffee_btns[2].getX()+50, coffee_btns[2].getY()+200, 200, 200);
		coffee_prices[2].setBounds(coffee_btns[2].getX()+60, coffee_btns[2].getY()+220, 100, 100);
		coffee_kinds[2].setBackground(new Color(0,0,0,0));
		coffee_prices[2].setBackground(new Color(0,0,0,0));
		
		coffee_kinds[3]=new JTextArea(cofe_menu[3]);
		coffee_prices[3]=new JTextArea(cofe_price[3]);
		coffee_kinds[3].setBounds(coffee_btns[3].getX()+50, coffee_btns[3].getY()+200, 200, 200);
		coffee_prices[3].setBounds(coffee_btns[3].getX()+60, coffee_btns[3].getY()+220, 100, 100);
		coffee_kinds[3].setBackground(new Color(0,0,0,0));
		coffee_prices[3].setBackground(new Color(0,0,0,0));
		
		coffee_kinds[4]=new JTextArea(cofe_menu[4]);
		coffee_prices[4]=new JTextArea(cofe_price[4]);
		coffee_kinds[4].setBounds(coffee_btns[4].getX()+50, coffee_btns[4].getY()+200, 200, 200);
		coffee_prices[4].setBounds(coffee_btns[4].getX()+60, coffee_btns[4].getY()+220, 100, 100);
		coffee_kinds[4].setBackground(new Color(0,0,0,0));
		coffee_prices[4].setBackground(new Color(0,0,0,0));
		
		coffee_kinds[5]=new JTextArea(cofe_menu[5]);
		coffee_prices[5]=new JTextArea(cofe_price[5]);
		coffee_kinds[5].setBounds(coffee_btns[5].getX()+50, coffee_btns[5].getY()+200, 200, 200);
		coffee_prices[5].setBounds(coffee_btns[5].getX()+60, coffee_btns[5].getY()+220, 100, 100);
		coffee_kinds[5].setBackground(new Color(0,0,0,0));
		coffee_prices[5].setBackground(new Color(0,0,0,0));
		
		coffee_kinds[0].setEditable(false); coffee_prices[0].setEditable(false); //JTextarea 편집불가
		coffee_kinds[1].setEditable(false); coffee_prices[1].setEditable(false);
		coffee_kinds[2].setEditable(false); coffee_prices[2].setEditable(false);
		coffee_kinds[3].setEditable(false); coffee_prices[3].setEditable(false);
		coffee_kinds[4].setEditable(false); coffee_prices[4].setEditable(false);
		coffee_kinds[5].setEditable(false); coffee_prices[5].setEditable(false);
		
		//메뉴 이미지 버튼
		coffee_box1.add(coffee_btns[0]);
		coffee_box1.add(coffee_btns[1]);
		coffee_box1.add(coffee_btns[2]);
		coffee_box1.add(coffee_btns[3]);
		coffee_box1.add(coffee_btns[4]);
		coffee_box1.add(coffee_btns[5]);
		
		coffee_box1.add(cofe_put);
		
		//메뉴 이름, 가격
		coffee_box1.add(coffee_kinds[0]); coffee_box1.add(coffee_prices[0]);
		coffee_box1.add(coffee_kinds[1]); coffee_box1.add(coffee_prices[1]);
		coffee_box1.add(coffee_kinds[2]); coffee_box1.add(coffee_prices[2]);
		coffee_box1.add(coffee_kinds[3]); coffee_box1.add(coffee_prices[3]);
		coffee_box1.add(coffee_kinds[4]); coffee_box1.add(coffee_prices[4]);
		coffee_box1.add(coffee_kinds[5]); coffee_box1.add(coffee_prices[5]);
		
		this.add(cofe_logo);
		this.add(coffee_box1);		
		
		this.setVisible(true);
		this.setResizable(false);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
//버튼9번 없애고 메뉴리스트 쌓이는 창에 구매하는거 넣어주기, 메뉴리스트에 쌓이도록 하는거 하기 지금메뉴 하나선택하면 새로운창에 떠서 안쌓임	
	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource()==coffee_btns[0]) { //아메리카노			
			cofe_counts = new CoffeeOfCounts(this, this.cofe_menu[0], this.cof_ameri, this.cofe_price[0], this.coffee_btns[0]);
		}
		
		else if(e.getSource()==coffee_btns[1]) { //복숭아 아이스티
			cofe_counts = new CoffeeOfCounts(this, this.cofe_menu[1], this.cof_pectea, this.cofe_price[1], this.coffee_btns[1]);			
		}
		
		else if(e.getSource()==coffee_btns[2]) { //라떼
			cofe_counts = new CoffeeOfCounts(this, this.cofe_menu[2], this.cof_lat, this.cofe_price[2], this.coffee_btns[2]);			
			
		}
		
		else if(e.getSource()==coffee_btns[3]) { //말차라떼
			cofe_counts = new CoffeeOfCounts(this, this.cofe_menu[3], this.cof_match_lat, this.cofe_price[3], this.coffee_btns[3]);			
			
		}
		
		else if(e.getSource()==coffee_btns[4]) { //아포가토
			cofe_counts = new CoffeeOfCounts(this, this.cofe_menu[4], this.cof_apog, this.cofe_price[4], this.coffee_btns[4]);			
			
		}
		
		else if(e.getSource()==coffee_btns[5]) { //퐁 크러쉬
			cofe_counts = new CoffeeOfCounts(this, this.cofe_menu[5], this.cof_pong, this.cofe_price[5], this.coffee_btns[5]);			
			
		}
		
		if(e.getSource() == cofe_put) {	
			check++;
			if(check==1)
				new orderingpay();
			
		}
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CoffeeOrdering();
		
	}

}

//if(cofe_menu[0].equals(cofe_counts.menu_nm.getText())) { //오류나면액션이벤트로 데리고가기
////if(menu_nm.equals(menu_nm.getText())) {
////		if(cofe_menu[0].equals(cofe_counts.ta.)) {
//coffee_btns[0].setEnabled(false);		//2개째부터 안됨	값들어온순간부터 false가 되도록 바꾸기
//}
//order_pay.menu_list[0] = this.cofe_menu[1];
//order_pay.menu_list[1] = cofe_counts.menu_count.getText();
//order_pay.menu_list[2] = cofe_counts.sum_price+"";
//order_pay = new orderingpay(this.cofe_menu[0], cofe_counts.menu_count.getText(), cofe_counts.sum_price+"");

//menu_list[0] = this.cofe_menu[0];
//menu_list[1] = cofe_counts.menu_count.getText();
//menu_list[2] = cofe_counts.sum_price+"";

//order_pay.menut.addRow(menu_list);

//order_pay = new orderingpay(this.cofe_menu[1], cofe_counts.menu_count.getText(), cofe_counts.sum_price+"");
//생성자로 넣는거 말고 다른거 써야될듯
//order_pay.menu_list[0] = this.cofe_menu[1];
//order_pay.menu_list[1] = cofe_counts.menu_count.getText();
//order_pay.menu_list[2] = cofe_counts.sum_price+"";

//menu_list[0] = this.cofe_menu[1];
//menu_list[1] = cofe_counts.menu_count.getText();
//menu_list[2] = cofe_counts.sum_price+"";
//
//order_pay.menut.addRow(menu_list);

//cofe_counts = new CoffeeOfCounts(boolean ena);
////장바구니에 들어간 목록 계산하기		
//if (btn_count == 1) {
//order_pay = new orderingpay();			
//}		
//
//menu_list[0] = menu_name.getText();
//menu_list[1] = menu_count.getText();
//menu_list[2] = menu_price.getText();
//
//
//this.menu_name.setText("");
//this.menu_count.setText("");
//this.menu_price.setText("");
//
//
//if(frie_count == 1) {
//order_pay.menut.addRow(menu_list);
//}

//new orderingpay(this.cofe_menu[2], cofe_counts.menu_count.getText(), cofe_counts.sum_price+"");
//order_pay.menu_list[0] = this.cofe_menu[2];
//order_pay.menu_list[1] = cofe_counts.menu_count.getText();
//order_pay.menu_list[2] = cofe_counts.sum_price+"";
//menu_list[0] = this.cofe_menu[2];
//menu_list[1] = cofe_counts.menu_count.getText();
//menu_list[2] = cofe_counts.sum_price+"";
//
//order_pay.menut.addRow(menu_list);