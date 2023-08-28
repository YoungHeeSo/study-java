package NotePad;

import java.awt.event.*;
import javax.swing.*;

public class Find extends JDialog implements ActionListener{
	private JLabel lFind = new JLabel("찾을 문자열: ");
	private JLabel lReplace = new JLabel("바꿀 문자열: ");
	private JTextField tFind = new JTextField(20);
	private JTextField tReplace = new JTextField(20);
	private JButton bFind = new JButton("찾기");
	private JButton bReplace = new JButton("바꾸기");
	private JTextArea ta;
	
	public Find(JFrame parent, JTextArea ta) {
		super(parent,"찾기",false);
		this.ta=ta;		
		setLayout(null); //레이아웃 설정
		//this.setSize(400, 400);
		
		lFind.setBounds(10,30,80,30); lReplace.setBounds(10,90,80,30);
		tFind.setBounds(90,30,150,30); tReplace.setBounds(90,90,150,30);
		bFind.setBounds(250,30,80,30); bReplace.setBounds(250,90,80,30);
		add(lFind); add(lReplace);
		add(tFind); add(tReplace);
		add(bFind); add(bReplace);
		
		this.setResizable(false);
		
		bFind.addActionListener(this);
		bReplace.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Find.this.dispose();
			}
		});
		
	}
	
	public void find() {
		String  str = tFind.getText();
		String text = ta.getText(); //비교대상
		//text = text.replace("\\r", ""); 
		int start = ta.getSelectionEnd();
		int end = text.length();
		int len = str.length();
		
		if(start == end) 
			start = 0;		
		for(; start<=end-len; start++) { //같아지는 순간까지만 검색
			if(text.substring(start, start+len).equals(str)) {
				ta.setSelectionStart(start);
				ta.setSelectionEnd(start+len);
				//ta.requestFocus();
				return;  //함수종료
			}
		}
		
		ta.setSelectionEnd(end);
		ta.setSelectionStart(end);
		ta.requestFocus();		
		
	}
	
	public void replace() {
		String str = tFind.getText();
		String rep = tReplace.getText();
		
		if(ta.getSelectedText()!=null && ta.getSelectedText().equals(str)) 
			ta.replaceRange(rep, ta.getSelectionStart(), ta.getSelectionEnd());		
		else
			find();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bFind) {//버튼의 객체이름확인
			find();
		}
		else if(e.getSource()==bReplace) {
			replace();
		}
	}
	
	public void showFind() {
		setTitle("찾기");
		this.setLocationRelativeTo(this);
		this.setSize(400,120);		
		this.setVisible(true);		
	}
	
	public void showReplace() {
		setTitle("찾아 바꾸기");
		this.setLocationRelativeTo(this);
		this.setSize(400,200);		
		this.setVisible(true);		
	}

}
