package khie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class _9_Counter extends JFrame{

	public _9_Counter() {
		
		setTitle("결제창");
		
		JPanel container1 = new JPanel(); //상단 컨테이너
		JPanel container2 = new JPanel(); //중앙 컨테이너
		JPanel container3 = new JPanel(); //하단 컨테이너
	
		Font bigFont = new Font("함초롬돋움", Font.BOLD, 30);
	  // 1. 컴포넌트들을 만들어 보자.
	  // 상단 컨테이너
	   JLabel jl1 = new JLabel("결제수단을 선택하세요.");
	   
	  // 중앙 컨테이너
	   JButton button1 = new JButton("카드");
	   JButton button2 = new JButton("현금");
	   button1.setPreferredSize(new Dimension(100,80));
	   button2.setPreferredSize(new Dimension(100,80));
	   button1.setBackground(Color.WHITE);
	   button2.setBackground(Color.WHITE);
	   
	   JLabel empty1 = new JLabel("      ");
	   
	   button1.setFont(bigFont);
	   button2.setFont(bigFont);
	   
	   // 하단 컨테이너
	   JButton button3 = new JButton("취   소");
	   button3.setBackground(Color.WHITE);
	   
	  // 2. 컴포넌트를 컨테이너에 올려야 한다.
	   container1.add(jl1);
	   container2.add(button1);
	   container2.add(empty1);
	   container2.add(button2);
	   container3.add(button3);
	   container1.setBackground(Color.ORANGE);
	   container2.setBackground(Color.ORANGE);
	   container3.setBackground(Color.ORANGE);
	   
	  // 3. 컨테이너를 프레임에 올려야 한다.
	   add(container1, BorderLayout.NORTH);
	   add(container2, BorderLayout.CENTER);
	   add(container3, BorderLayout.SOUTH);
	   
	   setBounds(300,300,300,200);
	   
	   // 연동 시 주석처리
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   setVisible(true);
	   
	   // 카드 버튼을 눌렀을 때
	   button1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			new _B_Card_02();	// 카드
			dispose();
		}
	});
	   
	   // 현금 버튼을 눌렀을 때
	   button2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			new _A_Cash_01();	// 현금
			dispose();
		}
	});
	   
	   // 취소 버튼을 눌렀을 때
	   button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

			dispose();
			}
		});
	   
	} 
	
	
//	public static void main(String[] args) {
//		
//		new _9_Counter();
//
//	}

}
