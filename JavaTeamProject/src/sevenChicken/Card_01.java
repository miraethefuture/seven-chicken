package sevenChicken;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Card_01 extends JFrame{
	
	Connection con = null;                  // DB와 연결하는 객체
	PreparedStatement pstmt = null;         // SQL문을 DB에 전송하는 객체
	ResultSet rs = null;                    // SQL문 실행 결과를 가지고 있는 객체
	String sql = null;  
	DefaultTableModel model;
	
	JTextField jtf1;

public Card_01() {
		
       setTitle("카드결제");
		
		// 컨테이너를 만들어야 한다.
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();
		
		
		// 1. 컴포넌트를 만들어야 한다.
		// 1-1. 상단에 들어갈 컴포넌트
		JLabel jl1 = new JLabel("카드결제 ");
		
        JTextArea jta = new JTextArea(8,20);
		
		JScrollPane jps = new JScrollPane(
				jta,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		// 1-2. 하단에 들어갈  컴포넌트
		JButton button1 = new JButton("조회");
		JButton button2 = new JButton("확인");
		JButton button3 = new JButton("취소");		
		
		container1.add(jl1);
		container2.add(jps);
		container3.add(button1);
		container3.add(button2);
		container3.add(button3);
		
		add(container1, BorderLayout.NORTH);
		add(container2, BorderLayout.CENTER);	
		add(container3, BorderLayout.SOUTH);
		
		setBounds(300,300,400, 250);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   
		setVisible(true);
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "결제하시겠습니까?",
						"확인", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.CLOSED_OPTION) {
					JOptionPane.showMessageDialog(null, "취소하셨습니다.");
				}else if(result == JOptionPane.YES_OPTION)
				 
				new Mileage();
				dispose();
				
			}
		});
		
          button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				 
				new Counter();
				dispose();
				
			}
		});
          
	    button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				connect();
				select();
				
		jta.append("총 금  액 : " + String.format("%,d원", price)+"\n");
		jta.append("카드 결제가 완료되었습니다.");
				
			}
		});
		
		
		
}		
void connect() {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.123.116:1521:xe";
	String user = "web";
	String password = "1234";
	
	
	try {
		Class.forName(driver);
		con= DriverManager.getConnection(url, user, password);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

void select() {
	
	try {
		sql = "select OUTPUT_PRICE from products where  PNUM = 5";
		
		pstmt = con.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int pirce = rs.getInt("OUTPUT_PRICE");
			
        Object[] data = {pirce};
        
        model.addRow(data);
			
		}
		rs.close(); pstmt.close(); con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}


	public static void main(String[] args) {
		
		new Card_01();
	}

}
