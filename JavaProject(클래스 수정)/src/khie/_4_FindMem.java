package khie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class _4_FindMem extends JFrame {
	
	Connection con = null;				
	PreparedStatement pstmt = null;		
	ResultSet rs = null;				
	String sql = null;	
	
	JTextField jtf1, jtf3;
	
	public _4_FindMem() {
		
		setTitle("회원 정보 찾기");
		
		JPanel container = new JPanel();
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();
		
		container1.setLayout(new GridLayout(2, 2));
		container2.setLayout(new GridLayout(1, 2));
		
		JLabel jl = new JLabel("회원 정보 찾기");
		jl.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jl1 = new JLabel("이   름 : ", JLabel.CENTER);
		namePanel.add(jl1);

		JPanel namePanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jtf1 = new JTextField(10);
		namePanel2.add(jtf1);
		
//		JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//		JLabel jl2 = new JLabel("연락처 : ", JLabel.CENTER);
//		phonePanel.add(jl2);
//
//		JPanel phonePanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		jtf2 = new JTextField(10);
//		phonePanel2.add(jtf2);
		
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jl3 = new JLabel("아이디 : ", JLabel.CENTER);
		idPanel.add(jl3);

		JPanel idPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jtf3 = new JTextField(10);
		idPanel2.add(jtf3);
		
	
		JPanel findidPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton findId = new JButton("아이디 찾기");

		JPanel findpwdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton findPwd = new JButton("비밀번호 찾기");
		
		JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton cancel = new JButton("취   소");
		
		findidPanel.add(findId);
		findpwdPanel.add(findPwd);
		
		cancelPanel.add(cancel);
		
		container.add(jl);
		
		container1.add(namePanel);
		container1.add(namePanel2);
//		container1.add(phonePanel);
//		container1.add(phonePanel2);
		container1.add(idPanel);
		container1.add(idPanel2);
		
		container2.add(findidPanel);
		container2.add(findpwdPanel);
		
		container3.add(cancelPanel);
	
		JPanel group1 = new JPanel(new BorderLayout());
		JPanel group2 = new JPanel(new BorderLayout());
		
		group1.add(container1, BorderLayout.NORTH);
		
		group2.add(container2, BorderLayout.NORTH);
		group2.add(container3, BorderLayout.CENTER);
		
		add(container, BorderLayout.NORTH);
		add(group1, BorderLayout.CENTER);
		add(group2, BorderLayout.SOUTH);
		

		setBounds(200, 200, 500, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		
		connect();
		
		// 이벤트 처리
		
		findId.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String id = findId(jtf1.getText());
				jtf3.setText(id);
				
				
			}
		});
				
		
		findPwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String pwd = findPwd(jtf3.getText());
				JOptionPane.showMessageDialog(null, pwd);
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connect();
				new _2_Log();
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
			// 1. 접속할 오라클 데이터베이스 드라이버를 메모리에 올리자
			Class.forName(driver);

			// 2. 오라클 데이터베이스와 연결을 시도
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	// connect() 메서드 end
	
	
	String findId(String str) {
		
		String id = "";
		
		try {
			sql = "select mem_id from membertable where mem_name = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, str);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				id = rs.getString("mem_id");
			}
			
			rs.close(); pstmt.close(); 
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return id;
	}	// findId() 메서드 end
	
	
	String findPwd(String str) {

		String pwd = "";

		try {
			sql = "select mem_pwd from membertable where mem_id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, str);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				pwd = rs.getString("mem_pwd");
			}

			rs.close();	pstmt.close(); con.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return pwd;

	}	// findPwd() 메서드 end
	

	public static void main(String[] args) {
		new _4_FindMem();

	}

}
