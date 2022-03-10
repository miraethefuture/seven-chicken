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

public class _2_Log extends JFrame {
	
	Connection con = null;				// DB와 연결하는 객체
	PreparedStatement pstmt = null;		// SQL문을 DB에 전송하는 객체
	ResultSet rs = null;				// SQL문 실행결과를 가지고 있는 객체
	String sql = null;
	
	JTextField jtf1;
	JPasswordField jtf2;
	
	static int count = 0;
	static int result = 0;
	
	static int cheesetrufflecount = 0, friedcount = 0, honeycount = 0, honeycombocount = 0, originalcount = 0, originalhalfcount = 0,
			redcount = 0, redcombocount = 0, redhoneyhalfcount = 0, salsalcount = 0, shinhwacount = 0, signaturesetcount = 0;
	static int cheeseballcount = 0, chickenburgercount = 0, chickenkatzcount = 0, chillypotatocount = 0, chipcasabacount = 0, gguabegicount = 0,
				potatowedgescount = 0, mucount = 0, redpicklecount = 0, saladcount = 0, saucehabaneromayocount = 0, sauceredcount = 0, 
				saucehoneygarliccount = 0, saucesweetchillycount = 0, saucetartarecount = 0;
	static int cokecount = 0, spritecount = 0, fantacount = 0, hanlasanbeercount = 0, honeysparklingcount = 0, draftbeercount = 0;
	
	public _2_Log() {
		
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
		jtf2 = new JPasswordField(10);
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
		

		count = 0;
		result = 0;
		
		cheesetrufflecount = 0; friedcount = 0; honeycount = 0; honeycombocount = 0; originalcount = 0; originalhalfcount = 0;
				redcount = 0; redcombocount = 0; redhoneyhalfcount = 0; salsalcount = 0; shinhwacount = 0; signaturesetcount = 0;
		cheeseballcount = 0; chickenburgercount = 0; chickenkatzcount = 0; chillypotatocount = 0; chipcasabacount = 0; gguabegicount = 0;
					potatowedgescount = 0; mucount = 0; redpicklecount = 0; saladcount = 0; saucehabaneromayocount = 0; sauceredcount = 0; 
					saucehoneygarliccount = 0; saucesweetchillycount = 0; saucetartarecount = 0;
		cokecount = 0; spritecount = 0; fantacount = 0; hanlasanbeercount = 0; honeysparklingcount = 0; draftbeercount = 0;
		
		// 이벤트 처리
		jlogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int re = Login(jtf1.getText(), jtf2.getText());
				
				// 로그인 성공
				if(re == 1) {
					confirm();
					updateCounttoZero();
					new _6_Menu();
					dispose();
				}
				
				// 비번 오류
				else if(re == -1) {
					JOptionPane.showMessageDialog(null, "존재하지 않거나 잘못된 ID입니다.");
				}
				
				else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				}
				
			}
		});
		

		join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new _3_Join();
				dispose();
				
			}
		});
		
		findMem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new _4_FindMem();
				dispose();
				
			}
		});
		
		// 관리자 모드 버튼을 눌렀을 시 실행 이벤트
		manager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int re = adminLogin(jtf1.getText(), jtf2.getText());
				// 로그인 성공
				if(re == 1) {
					new _F_Admin();
					dispose();
				}
				
				// 비번 오류
				else if(re == -1) {
					JOptionPane.showMessageDialog(null, "존재하지 않거나 관리자가 아닙니다.");
				}
				
				else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				}
				
				
			}
		});

		nm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				nonmem();
				updateCounttoZero();
				new _6_Menu();
				dispose();
			}
		});

	}
	
	
	// DB를 연동하는 메서드
	public void connect() {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";
		
		try {
			// 1. 접속할 오라클 데이터베이스 드라이버를 메모리에 올리자. - 동적 작업
			Class.forName(driver);
			
			// 2. 오라클 데이터베이스와 연결 시도
			con = DriverManager.getConnection(url, user, password);
			
//			if(con != null) {
//				JOptionPane.showMessageDialog(null, "DB 연결 성공");
//			} else {
//				JOptionPane.showMessageDialog(null, "DB 연결 실패");
//			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	} // connect() 메소드 end
	
	
	// 로그인 시 ID와 PWD가 DB와 일치하는지 확인
	int Login(String id, String pwd) {
		
		try {
			sql = "select mem_pwd from membertable where mem_id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(pwd)) {
					return 1;	// 로그인 성공
				} else {
					return 0;	// 비밀번호 오류
				}
			}else {
				return -1;	// 존재하지 않는 아이디
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;	// DB 오류
		}
		 
	}	// Login() 메서드 end
	
	// 관리자모드 입력 시  ID와 PWD가 일치하는지 확인
		int adminLogin(String id, String pwd) {

			try {
				sql = "select admin_pwd from admintable where admin_id = ?";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, id);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					if (rs.getString(1).equals(pwd)) {
						return 1; // 로그인 성공
					} else {
						return 0; // 비밀번호 오류
					}
				} else {
					return -1; // 존재하지 않는 아이디
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -2; // DB 오류
			}

		}	// adminLogin() 메서드 end
	
	// 로그인 클릭시 login_info에 id값 입력하는 메서드
	void confirm() {
			
		try {
			sql = "insert into login_info values (logininfo_seq.nextval, ?, sysdate || ' ' || TO_CHAR(SYSDATE, 'HH24:MI:SS'))";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, jtf1.getText());
			
			int res = pstmt.executeUpdate();
			
//			if(res > 0) {
//				JOptionPane.showMessageDialog(null, "회원 주문");
//			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	// confirm() 메서드 end
	
	
	// 비회원 주문 버튼 클릭시 메서드
	void nonmem() {
		
		try {
			sql = "insert into login_info values (logininfo_seq.nextval, '비회원', sysdate || ' ' || TO_CHAR(SYSDATE, 'HH24:MI:SS'))";
			
			pstmt = con.prepareStatement(sql);
					
			int res = pstmt.executeUpdate();
			
//			if(res > 0) {
//				JOptionPane.showMessageDialog(null, "비회원 주문");
//			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // nonmem() 메서드 end
	
	
	// 처음 실행 시 주문수량을 0으로 변경.
	void updateCounttoZero() {
	
		try {
			sql = "update menutable set Menu_count = 0 where Menu_count >= 0";
			
			pstmt = con.prepareStatement(sql);
			
			int res = pstmt.executeUpdate();
						
			pstmt.close();  // con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}	// updateCounttoZero() 메서드 end
	

//	public static void main(String[] args) {
//		
//		new _2_Log();
//
//	}

}
