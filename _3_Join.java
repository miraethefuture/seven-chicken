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

public class _3_Join extends JFrame {
	
	Connection con = null;				
	PreparedStatement pstmt = null;		
	ResultSet rs = null;				
	String sql = null;	
	
	JTextField id, name, phone, addr;
	JPasswordField pwd;
	
	
	public _3_Join() {
		
		setTitle("회원가입");
		
		JLabel title = new JLabel("회원가입", JLabel.CENTER);

		title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		JButton join = new JButton("회원가입");
		JButton cancel = new JButton("취소");

		id = new JTextField(10);
		pwd = new JPasswordField(10);
		name = new JTextField(10);
		phone = new JTextField(10);
		addr = new JTextField(10);

		// form panel
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		idPanel.add(new JLabel("이   름 : "));
		idPanel.add(name);

		JPanel pwdPanel = new JPanel();
		pwdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwdPanel.add(new JLabel("아이디 : "));
		pwdPanel.add(id);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		namePanel.add(new JLabel("비밀번호 : "));
		namePanel.add(pwd);

		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		phonePanel.add(new JLabel("주   소 : "));
		phonePanel.add(addr);
		
		JPanel addrPanel = new JPanel();
		addrPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		addrPanel.add(new JLabel("연락처 : "));
		addrPanel.add(phone);
		

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(5, 1));
		formPanel.add(idPanel);
		formPanel.add(pwdPanel);
		formPanel.add(namePanel);
		formPanel.add(phonePanel);
		formPanel.add(addrPanel);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(formPanel);

		JPanel panel = new JPanel();
		panel.add(join);
		panel.add(cancel);

		add(title, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);

		setBounds(200, 200, 250, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		connect();
		
		// 이벤트 처리
		join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				insert();
				
				name.setText(""); phone.setText("");
				id.setText(""); pwd.setText("");
				addr.setText("");
				name.requestFocus();
				
				new _2_Log();
				dispose();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new _2_Log();
				dispose();
				
			}
		});

	}	// 기본 생성자 end
	
	
	// 민초님 connect()
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
	
	
	// 회원 정보를 데이터베이스에 입력하는 메서드
	void insert() {
		
		try {
			sql = "insert into membertable values(member_seq.nextval, ?, ?, ?, '100', ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name.getText());
			pstmt.setString(2, id.getText());
			pstmt.setString(3, pwd.getText());
			pstmt.setString(4, addr.getText());
			pstmt.setString(5, phone.getText());
			
			int res = pstmt.executeUpdate();
			
			if (res > 0) {
				JOptionPane.showMessageDialog(null, "회원 가입 완료");
			}else {
				JOptionPane.showMessageDialog(null, "회원 가입 실패");
			}
			
			pstmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	// insert() 메서드 end
	

//	public static void main(String[] args) {
//		
//		new _3_Join();
//
//	}

}