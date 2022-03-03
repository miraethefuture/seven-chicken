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

import oracle.jdbc.driver.DBConversion;


public class Cash_01 extends JFrame{

	Connection con = null;                  // DB와 연결하는 객체
	PreparedStatement pstmt = null;         // SQL문을 DB에 전송하는 객체
	ResultSet rs = null;                    // SQL문 실행 결과를 가지고 있는 객체
	String sql = null;

	JTextField jtf1;
	JTextField jtf2;

	int price = 0;

	public Cash_01() {

		setTitle("현금결제");

		// 컨테이너를 만들어야 한다.
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();


		// 1. 컴포넌트를 만들어야 한다.
		// 1-1. 상단에 들어갈 컴포넌트
		JLabel jl1 = new JLabel("입  금  액 : ");
		JTextField money = new JTextField(8);

		connect();
		select();

		JLabel jl2 = new JLabel("결제 금액 : " + price);
//		JTextField jtf1 = new JTextField(8);


		JTextArea jta = new JTextArea(3,10);

		JScrollPane jps = new JScrollPane(
				jta,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// 1-2. 하단에 들어갈  컴포넌트
		JButton button1 = new JButton("계산");
		JButton button2 = new JButton("확인");

		container1.add(jl1);
		container1.add(money);
		container2.add(jl2);
//		container2.add(jtf1);
		container3.add(button1);
		container3.add(button2);

		JPanel group1 = new JPanel(new BorderLayout());

		group1.add(container1,BorderLayout.NORTH);
		group1.add(container2,BorderLayout.CENTER);
		group1.add(jps,BorderLayout.SOUTH);

		add(group1, BorderLayout.NORTH);
		add(container3, BorderLayout.CENTER);

		setBounds(300,300,400, 250);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

        button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				connect();
				select();

				int money1 = Integer.parseInt(money.getText());
				int total = Integer.parseInt(jtf1.getText());
				int result = money1 - total ;

				jta.append("총 금  액 : " + String.format("%,d원", total)+"\n");
				jta.append("입 금  액 : " + String.format("%,d원", money1)+"\n");
				jta.append("거스름돈 : " + String.format("%,d원", result)+"\n");

			}
		});



		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new Money_01();

			}
		});
	}


			void connect() {

                	String driver = "oracle.jdbc.OracleDriver";

            		String url ="jdbc:oracle:thin:@db202202171628_high?TNS_ADMIN=/Users/mirae/Downloads/Wallet_DB202202171628";
                    String userid="admin";
                    String pwd ="Happari13121312";


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

	    	sql = "select OUTPUT_PRICE from products1";

		     pstmt = con.prepareStatement(sql);

		     rs = pstmt.executeQuery();

		    while(rs.next()) {

		    	price = rs.getInt("OUTPUT_PRICE");

		           }
		      rs.close(); pstmt.close(); con.close();

	    } catch (SQLException e) {
		      // TODO Auto-generated catch block
		       e.printStackTrace();
	          }


}



	public static void main(String[] args) {

	     new Cash_01();

	}

}
