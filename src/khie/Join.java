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

public class Join extends JFrame {
	
	Connection con = null;				
	PreparedStatement pstmt = null;		
	ResultSet rs = null;				
	String sql = null;	
	
	JTextField id, name, phone, addr;
	JPasswordField pwd;
	
	public Join() {
		
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
		idPanel.add(new JLabel("아이디 : "));
		idPanel.add(id);

		JPanel pwdPanel = new JPanel();
		pwdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwdPanel.add(new JLabel("비밀번호 : "));
		pwdPanel.add(pwd);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		namePanel.add(new JLabel("이   름 : "));
		namePanel.add(name);

		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		phonePanel.add(new JLabel("연락처 : "));
		phonePanel.add(phone);
		
		JPanel addrPanel = new JPanel();
		addrPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		addrPanel.add(new JLabel("주   소 : "));
		addrPanel.add(addr);
		

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
		
		// 이벤트 처리
		join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connect();
				insert();
				
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Log();
				dispose();
				
			}
		});

	}	// 기본 생성자 end
	
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
	} // connect() 메서드 end
	
	// 회원 정보를 데이터베이스에 입력하는 메서드
	void insert() {
		
		
		
		try {
			sql = "insert into member values(?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id.getText());
			pstmt.setString(2, pwd.getPassword().toString());
			pstmt.setString(3, name.getText());
			pstmt.setString(4, phone.getText());
			pstmt.setString(5, addr.getText());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	

	public static void main(String[] args) {
		new Join();

	}

}
