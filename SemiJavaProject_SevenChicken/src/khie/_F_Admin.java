package khie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class _F_Admin extends JFrame {
	
	
	public _F_Admin() {
	
		// 컨테이너
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();
		
		// 맨 위 제목
		JLabel title = new JLabel("관리자 모드");
		
		title.setForeground(new Color(51, 51, 51));
		title.setFont(new FontUIResource("함초롬돋움", Font.BOLD, 25));
		
		// 버튼 - 각각의 창 링크 
		JButton product = new JButton("재고 관리");
		JButton	member = new JButton("회원 관리");
		JButton toLog = new JButton("로그인 화면으로");
		
		container1.add(title);
		container2.add(product);
		container2.add(member);
		container3.add(toLog);
		
		add(container1, BorderLayout.NORTH);
		add(container2, BorderLayout.CENTER);
		add(container3, BorderLayout.SOUTH);
		
		setBounds(200, 200, 300, 170);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// 재고 관리 버튼 클릭 시 
		product.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new _H_Product();
				dispose();

			}
		});
		
		
		// 회원 관리 버튼 클릭 시 
		member.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new _G_Member();
				dispose();
			
			}
		});
		
		// 로그인 화면으로 버튼 클릭 시
		toLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new _2_Log();
				dispose();
			}
		});
		
		
	} // 생성자 end
	
//	// 메인 메서드
//	public static void main(String[] args) {
//
//		new _F_Admin();
//		
//	} // main() end

	
} // Admin class {} end
