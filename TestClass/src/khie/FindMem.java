package khie;

import java.awt.Font;

import javax.swing.*;

public class FindMem extends JFrame {
	
	public FindMem() {

				
		
		
		setTitle("아이디 찾기");
				
		JLabel jl = new JLabel("아이디 찾기");
		
		jl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JLabel jl1 = new JLabel("이름 : ");
		JTextField jtf1 = new JTextField(10);
		
		JLabel jl2 = new JLabel("연락처 : ");
		JTextField jtf2 = new JTextField(10);
		
		JButton jb1 = new JButton("ID 찾기");
		
		
		
		
		
		
		setBounds(200, 200, 200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
				
		
	}

	public static void main(String[] args) {
		new FindMem();

	}

}
