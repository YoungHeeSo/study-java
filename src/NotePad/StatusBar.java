package NotePad;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
//SimpleDateFormat, BadLocationException
import java.util.Date;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;



public class StatusBar extends JPanel { //상태바 프로그래밍
	JPanel leftBar, midBar, rightBar;
	JLabel left, mid, right;
	JTextArea ta;
	
	public StatusBar(JFrame owner, JTextArea ta) {
		this.ta = ta; 
		left = new JLabel("A");
		mid = new JLabel("행 : "  + 1 + " 열 : " + 1);
		right = new JLabel("C");
		
		leftBar = new JPanel();
		leftBar.setBackground(new Color(230, 230, 230)); //색깔값 0~255빛의 색
		midBar = new JPanel();
		midBar.setBackground(new Color(20, 20, 20));
		rightBar = new JPanel();
		rightBar.setBackground(new Color(180, 180, 180));
		
		leftBar.add(left);
		midBar.add(mid);
		rightBar.add(right);
		
		this.setLayout(new GridLayout(1, 3)); //1행 3열로 행잡음
		this.add(leftBar);
		this.add(midBar);
		this.add(rightBar);
		
		ta.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent ex) {
				// TODO Auto-generated method stub
				int x=0, y=0;
			try {
				y = ta.getCaretPosition();	//누적된 열값이 나옴
				x = ta.getLineOfOffset(y); //행값
				int old_y = ta.getLineStartOffset(x); //빼는 값
				y = y-old_y;
	 			
			} catch (BadLocationException e) {
				e.printStackTrace();
			} 
			mid.setText("행 : " + (x+1) + " 열 : " + (y+1));
			}
		
		});
		
		Timer timer = new Timer(1000, new ActionListener() { //1초 간격으로 repaint() == 1초 간격으로 화면 변경
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
			}
			
		});
		timer.start();
	}

	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // 날짜를 생성해서 left 레일블에 표시
	    Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd aa HH:mm:ss");				
		left.setText(sd.format(d)); //현재 열값이 있는 라인 번호를 가져옴
		
		// 커서의 위치값 가져와서 mid 레이블에 표시
		
		//mid.setText("행 : " + 1 + " 열 : " + 1);	
		
		// 글짜크기 비율값 계산해서 right 레이블에 표시
		Font ft = ta.getFont();
		double rate = ft.getSize()/12.0 * 100; //기본폰트 12
		right.setText(String.format("%.0f", rate)+"%");
	}
	
}
