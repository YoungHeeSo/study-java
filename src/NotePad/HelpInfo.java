package NotePad;

import java.awt.Color;

import javax.swing.*;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;

public class HelpInfo extends JFrame {
	
	public HelpInfo() {
		this.setTitle("도움말정보");
		JTabbedPane t = new JTabbedPane(JTabbedPane.TOP); //TOP,left 등등 
		JPanel p1 = new JPanel(); p1.setBackground(Color.white);
		JPanel p2 = new JPanel(); p2.setBackground(Color.getHSBColor(50, 50, 20));
		JPanel p3 = new JPanel(); p3.setBackground(Color.getHSBColor(30, 10, 70));
		t.add("도움말", p1);
		t.add("메뉴소개", p2);
		t.add("고객센터안내", p3);
		this.setSize(450, 350);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.add(t);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HelpInfo();
	}

}
