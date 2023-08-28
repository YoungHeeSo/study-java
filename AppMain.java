import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import NotePad.Memojang;
import day.MemoCalendar;
import phone.playSwing;


public class AppMain extends JDialog{
	JButton app1, app2, app3, app4;
	JPanel pan = new JPanel();
	public AppMain(){
		this.setTitle("길동이의 자바보물창고");
		app1 = new JButton("메모장");
		app2 = new JButton("전화번호부");
		app3 = new JButton("일정관리");
		app4 = new JButton("내앱이름");
		app1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Memojang();
			}			
		});
		
		app2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new playSwing();
			}			
		});
		
		app3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MemoCalendar();				
			}			
		});
		
		app4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 여러분꺼...			
			}			
		});
		
		this.add(pan);		
		pan.add(app1); pan.add(app2); pan.add(app3); pan.add(app4);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);;
		this.setSize(400, 100);
		
		this.setLocation(1000, 200);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AppMain();
	}

}
