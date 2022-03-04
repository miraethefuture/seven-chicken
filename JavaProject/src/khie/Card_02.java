package khie;

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

import oracle.jdbc.driver.DBConversion;

public class Card_02 extends JFrame{
	
	Connection con = null;                  // DB와 연결하는 객체
	PreparedStatement pstmt = null;         // SQL문을 DB에 전송하는 객체
	ResultSet rs = null;                    // SQL문 실행 결과를 가지고 있는 객체
	String sql = null;


	int price = 0;
    int Pm = 0;
	
	public Card_02() {

		setTitle("카드결제");

		// 컨테이너를 만들어야 한다.
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();

		// 1. 컴포넌트를 만들어야 한다.
		// 1-1. 상단에 들어갈 컴포넌트
     	connect();
		select();

		JLabel jl1 = new JLabel("카드결제");
		JLabel jl2 = new JLabel("결제 금액 : " + price);


		JTextArea jta = new JTextArea(3,10);

		JScrollPane jps = new JScrollPane(
				jta,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// 1-2. 하단에 들어갈  컴포넌트
		JButton button1 = new JButton("조회");
		JButton button2 = new JButton("마일리지 사용");
		JButton button3 = new JButton("계산");
		JButton button4 = new JButton("취소");
        
		
		container1.add(jl1); container2.add(jl2);
		container3.add(button1);
		container3.add(button2);
		container3.add(button3);
		container3.add(button4);


		add(container1, BorderLayout.NORTH);
		add(container2, BorderLayout.NORTH);
		add(jps, BorderLayout.CENTER);
		add(container3, BorderLayout.SOUTH);

		setBounds(300,300,400, 250);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

        button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				connect();
				select();
				select2();

				int total = price;
				int mileage = total/10;
				int mileage1 = Pm;

				jta.append("결재금액 : " + String.format("%,d원", total)+"\n");			
				jta.append("현재 마일리지 : " + String.format("%,d점", mileage1)+"\n");
			}
		});

        
        button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int total = price;
				int mileage = Pm;
				int Nmileage = price - Pm;
				
				connect();
				select();
				select2();

				jta.append(" 남은 결제 금액 : " + String.format("%,d점", Nmileage)+"\n");	
				
			}
		});


		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

					
					int result = JOptionPane.showConfirmDialog(null, "결제하시겠습니까?",
							"확인", JOptionPane.YES_NO_OPTION);
					
					if(result == JOptionPane.CLOSED_OPTION) {
						JOptionPane.showMessageDialog(null, "취소하셨습니다.");
					}else if(result == JOptionPane.YES_OPTION)
						
				new Card_03();
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
	}


			void connect() {

                	String driver = "oracle.jdbc.OracleDriver";

            		String url ="jdbc:oracle:thin:@192.168.123.116:1521:xe";
                    String userid="web";
                    String pwd ="1234";


        		try {
        			Class.forName(driver);
        			con= DriverManager.getConnection(url, userid, pwd);

        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}

        	}

     void select() {

	    try {

	    	sql = "select OUTPUT_PRICE from products where  PNUM = 9";

		     pstmt = con.prepareStatement(sql);

		     rs = pstmt.executeQuery();

		    while(rs.next()) {

		    	price = rs.getInt("OUTPUT_PRICE");

		           }
		      rs.close(); pstmt.close(); //con.close();

	    } catch (SQLException e) {
		      // TODO Auto-generated catch block
		       e.printStackTrace();
	          }
}
     
     void select2() {
    	
    	 try {
    		 
    		sql = "select MILEAGE from products where  PNUM = 2";
    		 
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

		    	Pm = rs.getInt("MILEAGE");

		           }
			 rs.close(); pstmt.close(); con.close();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }

public static void main(String[] args) {
		
		new Card_02();
	}

}
