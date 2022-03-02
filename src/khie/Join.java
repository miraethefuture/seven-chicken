package khie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class Join extends JFrame {
	
	public Join() {
		
		setTitle("회원가입");
		
		JLabel title = new JLabel("회원가입", JLabel.CENTER);

		title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		JButton join = new JButton("회원가입");
		JButton cancel = new JButton("취소");

		JTextField id = new JTextField(10);
		JPasswordField pwd = new JPasswordField(10);
		JTextField name = new JTextField(10);
		JTextField phone = new JTextField(10);
		JTextField addr = new JTextField(10);

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
		
		
	}

	public static void main(String[] args) {
		new Join();

	}

}
