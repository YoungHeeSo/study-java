import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TabableView;

public class orderingpay extends JFrame implements ActionListener{
	String pay_img = "C:\\Users\\202216008\\Pictures\\javaimg\\order_btn_img.png";	
	JButton order_btn, remove_btn;
	static JTable jordt;
	static JScrollPane jsm;
	static DefaultTableModel menut;
	
	CoffeeOrdering cofe_order;
	CoffeeOfCounts cofe_counts;
	
	static String[] menu_list = new String[3];
	static String[][] sum_of = {menu_list}; 
	TableModel tamo = cofe_counts.ta.getModel();
	JOptionPane op = new JOptionPane();	

	public orderingpay() {
		this.setTitle("계산하셔야죵?");
		this.setLayout(null);
		this.setBounds(500, 700, 500, 500);	
				
//		String[] title= {"메뉴","개수","가격"};
//		Object[][] row= new Object[0][2];
//		menut = new DefaultTableModel(row, title);
//		jordt = new JTable(menut);
		
		jsm = new JScrollPane(cofe_counts.ta); 
		jsm.setBounds(30, 100, 360, 370);
		
		JPanel menubar = new JPanel();		
		
		order_btn = new JButton("주문 버튼 입니다");
		remove_btn = new JButton("취소 버튼 입니다");		
		menubar.setBounds(10, 10, 380, 100);
		
		order_btn.addActionListener(this);
		remove_btn.addActionListener(this);
		
		System.out.println("order_pay> "+menu_list[1]);
		menubar.add(order_btn);	
		menubar.add(remove_btn);		
		
		this.add(jsm);
		this.add(menubar);
		
		this.setResizable(false); //창크기조절불가
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int sum=0;		
		int zero=cofe_counts.ta.getRowCount();
		if(e.getSource() == remove_btn) {
			if(cofe_counts.ta.getRowCount() >= 1){
				zero = 0;
				System.out.println(zero);
				DefaultTableModel mo = (DefaultTableModel)cofe_counts.ta.getModel();
				mo.setNumRows(0);
			}
		}
		
			
		if(zero == 0) {
			cofe_order.cofe_put.setEnabled(false);			
			for(int i=0; i<cofe_order.cofe_menu.length; i++) {
				System.out.println(cofe_order.cofe_menu[i]);
				cofe_order.coffee_btns[i].setEnabled(true);
			}	
			setVisible(false);
		}
		
		
		if(e.getSource() == order_btn) {		
			System.out.println("menu_list > "+Arrays.deepToString(sum_of));
			for(int idx=0; idx<tamo.getRowCount(); idx++) {		
				for(int cdx=0; cdx<tamo.getColumnCount(); cdx++) {
					Object val = tamo.getValueAt(idx, cdx);
					if(cdx==0) {
						System.out.println(val.toString());
					}
					else if(cdx==2) {
						int num= Integer.parseInt(val.toString());
						sum+=num;
						System.out.println("보여?"+sum);
					}				
				}
			}
			int order = JOptionPane.showConfirmDialog(null, "결제 금액은 "+sum+"원 입니다", "결제 할까요?",JOptionPane.YES_NO_OPTION);
			if(order == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "결제 되었습니다", "",JOptionPane.PLAIN_MESSAGE);
				System.exit(HIDE_ON_CLOSE);
				
			}				
			else
				return;
		}			
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new orderingpay();
	}

}