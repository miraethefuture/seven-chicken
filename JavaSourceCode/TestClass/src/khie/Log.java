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

public class Log extends JFrame {
	
	Connection con = null;				// DB와 연결하는 객체
	PreparedStatement pstmt = null;		// SQL문을 DB에 전송하는 객체
	ResultSet rs = null;				// SQL문 실행결과를 가지고 있는 객체
	String sql = null;
	
	JTextField jtf1, jtf2;
	

	
	public Log() {

		setTitle("SEVEN 치킨 주문");
		
		JPanel title = new JPanel();
		
		// title container에 들어갈 컴포넌트를 만들자
		JLabel login = new JLabel("로그인 화면");
		login.setFont(new Font("맑은 고딕", Font.BOLD, 25));

		// "로그인 화면" JLabel을 title 컨테이너에 올려주자.
		title.add(login);

		JPanel container = new JPanel();
		container.setLayout(new GridLayout(4, 2));

		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jl1 = new JLabel("아이디 : ", JLabel.CENTER);
		idPanel.add(jl1);

		JPanel idPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jtf1 = new JTextField(10);
		idPanel2.add(jtf1);

		container.add(idPanel);
		container.add(idPanel2);

		JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jl2 = new JLabel("비밀번호 : ", JLabel.CENTER);
		pwdPanel.add(jl2);
		
		JPanel pwdPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jtf2 = new JTextField(10);
		pwdPanel2.add(jtf2);
		
		container.add(pwdPanel);
		container.add(pwdPanel2);

		JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton jlogin = new JButton("로그인");

		JPanel joinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton join = new JButton("회원가입");
		
		JPanel findPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton findMem = new JButton("회원 정보 찾기");

		JPanel managerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton manager = new JButton("관리자 모드");
		
		JPanel nonmemberPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton nm = new JButton("비회원 주문");
		
		loginPanel.add(jlogin);
		joinPanel.add(join);
		findPanel.add(findMem);
		managerPanel.add(manager);
		nonmemberPanel.add(nm);
		
		container.add(loginPanel);
		container.add(joinPanel);
		container.add(findPanel);
		container.add(managerPanel);
		

		JPanel jp2 = new JPanel();

		jp2.setLayout(new FlowLayout());

		jp2.add(container);


		add(title, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
		add(nonmemberPanel, BorderLayout.SOUTH);

		setBounds(200, 200, 400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		connect();
		
		
		// 이벤트 처리
		jlogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String pwd = Login(jtf1.getText());
				
				if(pwd.equals(jtf2.getText())) {
					JOptionPane.showMessageDialog(null, "성공");					
				}else {
					JOptionPane.showMessageDialog(null, "실패");
				}
				
				
			}
		});
		

		join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Join();
				dispose();
				
			}
		});
		
		findMem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new FindMem();
				dispose();
				
			}
		});
		
		manager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		nm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// new Menu();
				
			}
		});

	}
	
	
	void connect() {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
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
	
	
	String Login(String str) {
		
		String pwd = "";
		
		try {
			sql = "select mem_pwd from membertable where mem_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, str);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pwd = rs.getString("mem_pwd");				
			}
			
			rs.close(); pstmt.close(); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pwd;
		
	}
	

	public static void main(String[] args) {
		
		new Log();

	}

}
