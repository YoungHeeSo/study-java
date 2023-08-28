import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class OrderingFood extends JFrame implements ActionListener{
	String ck_img_btn = "C:\\Users\\202216008\\Pictures\\javaimg\\ck_btn_img.jpg";
	String coffe_background_img = "C:\\Users\\202216008\\Pictures\\javaimg\\coffee.png";
	
	JButton cofe_btn, piz_btn, pay_btn, cancel_btn;
	JPanel food_ck;
	JLabel bg_label;
	
	ImageIcon ck_img, coffee_background;
	int orco=0;
		
	OrderingFood(){		
		this.setTitle("소영의 카페하루");
		this.setLayout(null);
		this.setBounds(0, 0, 800, 600);
		
		coffee_background = new ImageIcon(coffe_background_img);
		
		food_ck = new JPanel();	
		bg_label = new JLabel(coffee_background);
		
		ck_img = new ImageIcon(ck_img_btn);	
		
		cofe_btn = new JButton("음료 주문하기");
		cancel_btn = new JButton("닫기");
		
		cofe_btn.addActionListener(this);
		cancel_btn.addActionListener(this);		
		
		bg_label.setBounds(0, 0, 800, 600);
		food_ck.setBounds(250, 300, 200, 50);
		food_ck.setBackground(new Color(0,0,0,0));
		
		food_ck.add(cofe_btn);
		food_ck.add(cancel_btn);
	
		this.add(food_ck);
		this.add(bg_label);		
		
		this.setResizable(false); //창사이즈 변경불가		
		this.setVisible(true);
	
				
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == cofe_btn) {	
			orco++;
			if(orco==1) {
				new CoffeeOrdering();
				//this.setVisible(false);
			}
				
		}
		
		if(e.getSource() == cancel_btn) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new OrderingFood();
	}

	

}