<<<<<<< HEAD:JavaProject/src/khie/_E_Mileage.java
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

public class _E_Mileage extends JFrame{

	Connection con = null;                  // DB와 연결하는 객체
	PreparedStatement pstmt = null;         // SQL문을 DB에 전송하는 객체
	ResultSet rs = null;                    // SQL문 실행 결과를 가지고 있는 객체
	String sql = null;

	int Pm = 0;

	public _E_Mileage() {

		setTitle("마일리지(회원)");

		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();

		JPanel container5 = new JPanel();
		JPanel container6 = new JPanel();
		JPanel container7 = new JPanel();

		JLabel jl1 = new JLabel("환영합니다 ");
		JLabel jl2 = new JLabel("고객님");
		JLabel jl3 = new JLabel("사용 가능 포인트");

		JTextArea jta = new JTextArea(3,10);

		JScrollPane jsp = new JScrollPane(
				jta,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JButton check = new JButton("마일리지 조회");
		JButton change = new JButton("회원정보변경");
		JButton logout = new JButton("로그아웃");

		container1.add(jl1);
		container2.add(jl2);

		container3.add(jl3);

		container5.add(check);
		container6.add(change);
		container7.add(logout);

		JPanel group1 = new JPanel(new BorderLayout());
		JPanel group2 = new JPanel(new BorderLayout());
		JPanel group3 = new JPanel(new BorderLayout());

		group1.add(container1, BorderLayout.NORTH);
		group1.add(container2, BorderLayout.SOUTH);

		group2.add(container3,BorderLayout.NORTH);
		group2.add(jsp,BorderLayout.CENTER);

		group3.add(container5,BorderLayout.NORTH);
		group3.add(container6,BorderLayout.CENTER);
		group3.add(container7,BorderLayout.SOUTH);

		add(group1,BorderLayout.NORTH);
		add(group2,BorderLayout.CENTER);
		add(group3,BorderLayout.SOUTH);

		setBounds(400, 400, 250, 400);



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

		check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				connect();
				select();

				int mileage1 = Pm;

				jta.append("현재 마일리지 : " + String.format("%,d점", mileage1)+"\n");
			}
		});

		change.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				//new
				dispose();
			}
		});




		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//new
				dispose();
			}
		});


	}
	void connect() {

		String driver = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@192.168.0.4:1521:xe";

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

      new _E_Mileage();
	}

}
