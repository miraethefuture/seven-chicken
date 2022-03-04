package khie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class Admin extends JFrame {
	
	
	public Admin() {
	
		// 컨테이너
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		
		// 맨 위 제목
		JLabel title = new JLabel("관리자 모드");
		
		title.setForeground(new Color(51, 51, 51));
		title.setFont(new FontUIResource("휴먼편지체", Font.ITALIC, 25));
		
		// 버튼 - 각각의 창 링크 
		JButton product = new JButton("재고 관리");
		JButton	member = new JButton("회원 관리");
		
		container1.add(title);
		container2.add(product);
		container2.add(member);
		
		add(container1, BorderLayout.NORTH);
		add(container2, BorderLayout.CENTER);
		
		
		setBounds(200, 200, 300, 150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// 재고 관리 버튼 클릭 시 
		product.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Product();
				dispose();
				
				
			
			}
		});
		
		
		// 회원 관리 버튼 클릭 시 
		member.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Member();
				dispose();
			
			}
		});
		
		
	} // 생성자 end
	
	// 메인 메서드
	public static void main(String[] args) {

		new Admin();
		
	} // main() end

	
} // Admin class {} end
