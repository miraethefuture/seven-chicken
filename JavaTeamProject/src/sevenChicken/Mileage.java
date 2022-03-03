package sevenChicken;

import java.awt.BorderLayout;

import javax.swing.*;

public class Mileage extends JFrame{

	public Mileage() {
	       
		setTitle("마일리지(회원)");
		
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();
		JPanel container4 = new JPanel();
		JPanel container5 = new JPanel();
		JPanel container6 = new JPanel();
		
		JLabel jl1 = new JLabel("환영합니다 ");
		JLabel jl2 = new JLabel("고객님");
		JLabel jl3 = new JLabel("사용 가능 포인트");
		
		JTextArea jta = new JTextArea(3,10);
		
		JScrollPane jsp = new JScrollPane(
				jta,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton change = new JButton("정보 조회/수정");
		JButton delete = new JButton("회원탈퇴");
		JButton logout = new JButton("로그아웃");
		
		container1.add(jl1);
		container2.add(jl2);
		
		container3.add(jl3);
		container4.add(change);
		
		container5.add(delete);
		container6.add(logout);
		
		JPanel group1 = new JPanel(new BorderLayout());
		JPanel group2 = new JPanel(new BorderLayout());
		JPanel group3 = new JPanel(new BorderLayout());

		group1.add(container1, BorderLayout.NORTH);
		group1.add(container2, BorderLayout.SOUTH);
		
		group2.add(container3,BorderLayout.NORTH);
		group2.add(jsp,BorderLayout.CENTER);
		
		group3.add(container4,BorderLayout.NORTH);
		group3.add(container5,BorderLayout.CENTER);
		group3.add(container6,BorderLayout.SOUTH);
		
		add(group1,BorderLayout.NORTH);
		add(group2,BorderLayout.CENTER);
		add(group3,BorderLayout.SOUTH);
		
		setBounds(400, 400, 250, 400);
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		
      new Mileage();
	}

}
