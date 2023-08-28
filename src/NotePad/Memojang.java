package NotePad;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Memojang extends JFrame implements ActionListener{
	//전역변수 영역
	JTextArea ta = new JTextArea();
	JButton newBtn, openBtn, saveBtn, exitBtn, copyBtn, pastBtn, cutBtn, fontBtn, colBtn; //새파일, 열기, 저장하기, 나가기, 복사, 붙여넣기, 폰트, 글자색 16x16 or 20x20
	StatusBar stBar;
	JFileChooser jfc; //변수선언
	File file = null; //파일 객체변수
	boolean isNew= false; //새로운 입력내용이 발생했는지check, 새로운입력내용이 발생했는지 체크. 저장이 필요한지 여불를 체크(false:저장불필요)
	String filename=null; //불러올 , 저장할 파일이름 저장 변수
	FontStyleView fontStyleView;
	
	//메모장 창
	public Memojang() {		
		this.setTitle("소영이의 메모장");
		 JScrollPane sp = new JScrollPane(ta);
		 this.add(sp);		 
		 
		 this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ta.requestFocus();
			}
			
		 });
		 
		 ta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				isNew=true;
			}
			 
		 });
		 
		 makeMenu();
		 jfc = new JFileChooser(); //객체생성                       
		 
		 //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		 this.setBounds(1000, 200, 500, 500);
		 this.setVisible(true);
	}
	
	//메모장안 메뉴바
	public void makeMenu() {
		//최상단 메뉴바 
		JMenuBar mb = new JMenuBar();
		JToolBar tb = new JToolBar();
		stBar = new StatusBar(this, this.ta);
		
		/////// JToolBar 메뉴 ////////
		ImageIcon newF = new ImageIcon("image/newBtn.png");
		ImageIcon openF = new ImageIcon("image/oftenBtn.png");
		ImageIcon saveF = new ImageIcon("image/saveBtn.png");
		ImageIcon exitF = new ImageIcon("image/exitBtn.png");
		ImageIcon copyF = new ImageIcon("image/copyBtn.png");
		ImageIcon pastF = new ImageIcon("image/pastBtn.png");
		ImageIcon cutF = new ImageIcon("image/cutBtn.png");
		ImageIcon fontF = new ImageIcon("image/fontBtn.png");
		ImageIcon colF = new ImageIcon("image/colBtn.png");
		
		//이미지버트만들기
		newBtn = new JButton(newF); 
		openBtn = new JButton(openF);
		saveBtn = new JButton(saveF);
		exitBtn = new JButton(exitF);
		copyBtn = new JButton(copyF);
		pastBtn = new JButton(pastF);
		cutBtn = new JButton(cutF);
		fontBtn = new JButton(fontF);
		colBtn = new JButton(colF);
		
		newBtn.addActionListener(this);
		openBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		copyBtn.addActionListener(this);
		pastBtn.addActionListener(this);
		cutBtn.addActionListener(this);
		fontBtn.addActionListener(this);
		colBtn.addActionListener(this);
		
		//툴박스
		newBtn.setToolTipText("새파일 입니다"); openBtn.setToolTipText("파일열기 입니다"); saveBtn.setToolTipText("저장 합니다");
		exitBtn.setToolTipText("나가기 입니다"); copyBtn.setToolTipText("복사 입니다");
		pastBtn.setToolTipText("붙여넣기 입니다"); cutBtn.setToolTipText("자르기 입니다");
		fontBtn.setToolTipText("폰트 입니다"); colBtn.setToolTipText("폰트색 입니다");
		
		
		tb.add(newBtn); tb.add(openBtn); tb.add(saveBtn); tb.add(exitBtn);
		tb.addSeparator();
		tb.add(copyBtn); tb.add(pastBtn); tb.add(cutBtn);
		tb.addSeparator();
		tb.add(fontBtn); tb.add(colBtn);
		
				
		//파일메뉴
		JMenu file = new JMenu("파일");
		JMenuItem file_new = new JMenuItem("새로만들기");
		JMenuItem file_open = new JMenuItem("열기");
		JMenuItem file_save = new JMenuItem("저장");
		JMenuItem file_saveAs = new JMenuItem("다른이름으로저장");
		JMenuItem file_exit = new JMenuItem("끝내기");
		
		file_new.addActionListener(this);
		file_open.addActionListener(this);
		file_save.addActionListener(this);
		file_saveAs.addActionListener(this);
		file_exit.addActionListener(this);
		
		/////////파일메뉴////////////
		file.add(file_new); file.add(file_open);
		file.addSeparator();
		file.add(file_save); file.add(file_saveAs); 
		file.addSeparator();
		file.add(file_exit);
		
		//편집메뉴
		JMenu edit = new JMenu("편집");
		JMenuItem edit_cut = new JMenuItem("잘라내기");
		JMenuItem edit_copy = new JMenuItem("복사하기");
		JMenuItem edit_paste = new JMenuItem("붙여넣기");
		JMenuItem edit_find = new JMenuItem("찾기");
		JMenuItem edit_replace = new JMenuItem("바꾸기");
		JMenuItem edit_datetime = new JMenuItem("시간/날짜");
		JMenuItem edit_all = new JMenuItem("전체선택");
		
		edit_cut.addActionListener(this);
		edit_copy.addActionListener(this);
		edit_paste.addActionListener(this);
		edit_find.addActionListener(this);
		edit_replace.addActionListener(this);
		edit_datetime.addActionListener(this);
		edit_all.addActionListener(this);
		
		//////////////////////
		edit.add(edit_cut); edit.add(edit_copy); edit.add(edit_paste);
		edit.addSeparator();
		edit.add(edit_find);edit.add(edit_replace); 
		edit.addSeparator();
		edit.add(edit_datetime);edit.add(edit_all); 
		
		////////////
		edit_cut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.cut();
			}
		});
		
		edit_copy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.copy();
			}
		});
		
		edit_paste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.paste();
			}
		});
		
		edit_all.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.selectAll();
			}
		});
		
		edit_datetime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//시간/날짜 작성하기 데이트랑 심플데이트포맷
				 Date d = new Date();
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd aa HH:mm:ss");				
					ta.append(sd.format(d));
			}
		});		
		
			
		/////서식 메뉴바
		JMenu form = new JMenu("서식");
		JMenuItem form_font = new JMenuItem("글꼴");
		JMenuItem form_fontColor = new JMenuItem("글자색");
		JMenuItem form_backColor = new JMenuItem("배경색");
		
		form.add(form_font);
		form.add(form_fontColor);
		form.add(form_backColor);
		
		//글꼴바꾸기 방학숙제이다~~~슈ㅠㅠㅠㅠㅠ, 폰트파일 분석하긔
		form_font.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fontStyleView = new FontStyleView(ta);
				fontStyleView.setBounds(200, 200, 400, 350);
				fontStyleView.setVisible(true);				
				
			}
		});
		
		form_backColor.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.yellow);
				if(setColor != null)
					ta.setBackground(setColor);
			}
		});
		
		form_fontColor.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.yellow);
				if(setColor != null) //색을 선택했으면
					ta.setForeground(setColor); //선택한 색으로 바꿔줌
			}
		});
		
		
		/////보기 메뉴바
		JMenu view = new JMenu("보기");
		JMenuItem view_zoom = new JMenuItem("확대/축소");
		JCheckBoxMenuItem view_statusBar = new JCheckBoxMenuItem("상태표시줄");
		edit.add(view_zoom);edit.add(view_statusBar); 
		
		view_statusBar.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(view_statusBar.getState() ==true)
					stBar.setVisible(true);
				else
					stBar.setVisible(false);
			}
		});
		
		
		
		/////도움말 메뉴바
		JMenu help = new JMenu("도움말");
		JMenuItem help_info = new JMenuItem("도움말정보");
		JMenuItem help_about = new JMenuItem("메모장정보");
		
		help_about.addActionListener(this);
		help_info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HelpInfo();
			}
			
		}); 
		help.add(help_info); help.addSeparator(); 
		help.add(help_about);
		
		mb.add(file); mb.add(edit); mb.add(form); mb.add(view); mb.add(help);
		
		this.setJMenuBar(mb);
		this.add(tb, BorderLayout.NORTH);
		this.add(BorderLayout.SOUTH, stBar);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("메모장정보")) {
			new AboutMemojang(this);
		}
		
		else if(cmd.equals("새로만들기") || e.getSource()==newBtn) { //조건이 눌리면
			this.setTitle("소영이네 메모장");
			filename=null;
			saveCheck();
		}
		
		else if(cmd.equals("열기") || e.getSource()==openBtn) { //조건이 눌리면
			int a = saveCheck();
			if(a==0)
				open();
		}
		
		else if(cmd.equals("저장") || e.getSource()==saveBtn) { //조건이 눌리면
			save();
		}
		
		else if(cmd.equals("다른이름으로저장")) { //조건이 눌리면
			saveAs();
		}
		
		else if(cmd.equals("끝내기") || e.getSource()==exitBtn) { //조건이 눌리면
			int a = saveCheck();
			if(a==0)
				this.setVisible(false);
				//System.exit(1);
		}
		
		else if(cmd.equals("복사하기") || e.getSource()==copyBtn) { //조건이 눌리면
			ta.copy();
		}
		
		else if(cmd.equals("붙여하기") || e.getSource()==pastBtn) { //조건이 눌리면
			ta.paste();
		}
		
		else if(cmd.equals("잘라내기") || e.getSource()==cutBtn) { //조건이 눌리면
			ta.cut();
		}
		
		else if(cmd.equals("글꼴") || e.getSource()==fontBtn) { //조건이 눌리면
			fontStyleView = new FontStyleView(ta);
			fontStyleView.setBounds(200, 200, 400, 350);
			fontStyleView.setVisible(true);	
		}
		
		else if(cmd.equals("글자색") || e.getSource()==colBtn) { //조건이 눌리면
			Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.yellow);
			if(setColor != null) //색을 선택했으면
				ta.setForeground(setColor); //선택한 색으로 바꿔줌
		}
		
		else if(cmd.equals("찾기")) { //조건이 눌리면
			Find fi = new Find(this, ta);
			fi.showFind();
		}
		
		else if(cmd.equals("바꾸기")) { //조건이 눌리면
			Find fi = new Find(this, ta);
			fi.showReplace();
		}
//		
	}
	
	public int saveCheck(){
		int val=0;
		if(isNew==true) {
			int yesno = JOptionPane.showConfirmDialog(this, "저장할 내용이 있습니다 저장하시겠습니까?");
			if(yesno==JOptionPane.YES_OPTION) 
			{
				save(); 
				ta.setText(""); //깔끔하게 사라져라~(초기화)
			}
			else if(yesno==JOptionPane.NO_OPTION) {
				ta.setText(""); //깔끔하게 사라져라~(초기화)
				isNew=false;
			}
			else if(yesno == JOptionPane.CANCEL_OPTION) {
				val = 1;
			}
			
		}return val;
		
	}
	
	//열기 설정!!!!!!!!!!!!
	public void open() {
		int re = jfc.showOpenDialog(this); //j프레임 안에서 창을 나타나게 함 //open을 save바꾸면 저장창으로바뀜
		if (re == jfc.APPROVE_OPTION) { //열기버튼은 0, 취소버튼은 1로 설정
			file = jfc.getSelectedFile();
			this.setTitle(file.getAbsolutePath() + "-소영이네 메모장"); //타이틀에 파일경로
			String data= ""; 
			int ch; //한글자씩 읽어옴
			try { //이부분은 파일을 읽는부분
				ta.setText("");
				FileReader fr = new FileReader(file); //파일내용읽어옴 13장참고 file을 가리키고 있는!!!!!!!예외처리가 반드시필요, 안되면 오류처리되어버림
				BufferedReader br = new BufferedReader(fr);
				String dd = br.readLine();
				while((dd=br.readLine())!=null)
					ta.append(dd + System.lineSeparator());
					ta.append(dd);
//					ta.append(dd + "\n"); 
					
// 				while((ch = fr.read()) != -1) //파일을 다 읽으면-1이 읽히는데 파일의 끝EOF, -1을 읽으면 while문 나가서 data값 읽음
//					data = data + (char) ch;
//				ta.setText(data); //읽으면서 여기에 누적시킴
					
				br.close();
				fr.close();
				isNew = false; //저장될 변화가 없는 상태false
				filename=file.getName();
			} catch (FileNotFoundException e) {
				e.getMessage();
			}
			
			catch (IOException e) {
			e.getMessage();
			}
		}
	}
	
	public void save() { //save, 다른이름저장
		if(filename==null) {//파일이름이 설정된 적이 없다면
			int re = jfc.showSaveDialog(this);
			if (re == jfc.APPROVE_OPTION) {
				file = jfc.getSelectedFile();
				filename=file.getName();
			}
		}
			
		String dd= ta.getText();
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(dd);
			
			fw.close();
			this.setTitle(file.getAbsolutePath() + "-소영이네 메모장");
			isNew=false;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
public void saveAs() { //save, 다른이름저장
		int re = jfc.showSaveDialog(this);
		filename=file.getName();
		System.out.println(filename);
		if (re == jfc.APPROVE_OPTION) {
			file = jfc.getSelectedFile();
			String dd= ta.getText();
			try {
				FileWriter fw = new FileWriter(file);
				fw.write(dd);
				
				fw.close();
				this.setTitle(file.getAbsolutePath() + "-소영이네 메모장");
				isNew=false;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Memojang();
		      
	}

}

