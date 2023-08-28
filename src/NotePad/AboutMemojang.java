package NotePad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AboutMemojang extends JDialog {
	public AboutMemojang(Frame owner) {
		super(owner, "메모장 정보", false); //modal(true)대화상자 띄어놓은 상태에서 다른 작업이불가능 modaless(false)대화상자 띄어놓은 상태에서 다른 작업이가능!
		ImageIcon ticon = new ImageIcon(AboutMemojang.class.getResource("jicon.png")); //타이틀 아이z콘
	    Image timg = ticon.getImage();
		
		this.setSize(490, 250);
		this.setLocationRelativeTo(owner);
		this.setIconImage(timg);
		this.setVisible(true);
		
		JPanel p1 = new JPanel(); //텍스트 패널
		JTextArea ta1 = new JTextArea();
		ta1.setText("소영이의 메모장 입니다!\n버전 정보 OS빌드 1.0.1 자바 이클립스\n운영 및 해당사용자 인터페이스는\n오직 자바에서만 상표권 및 출원을 합니다\n"
				+ "이 제품은 이클립스 소프트웨어 사용 조건에 따라\n다음 사용자가 사라질 수 있음");
		ta1.setEditable(false); //편집불가
		
		JLabel imgLabel = new JLabel();  
        ImageIcon icon = new ImageIcon(AboutMemojang.class.getResource("java.png"));
        
        imgLabel.setIcon(icon);
        imgLabel.setHorizontalAlignment(JLabel.RIGHT);
		
    	add(imgLabel);
    	p1.add(ta1);
		p1.add(imgLabel);
		add(p1);
		
	}
	
}

















		
//		JLabel imgLabel = new JLabel();  
//        ImageIcon icon = new ImageIcon(AboutMemojang.class.getResource("java.png"));
//        
//        Image img = icon.getImage();
//    	Image updateImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
//        ImageIcon updateIcon = new ImageIcon(updateImg);
//        
//        imgLabel.setIcon(updateIcon);
//        
//        imgLabel.setBounds(210, 30, 165, 150);
//        imgLabel.setHorizontalAlignment(JLabel.RIGHT);
//		
//    	getContentPane().add(imgLabel);
    	
 
