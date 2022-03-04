package sevenChicken;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Counter extends JFrame{

	public Counter() {
		
		setTitle("결제 화면");
		
		JPanel container1 = new JPanel(); //상단 컨테이너
		JPanel container2 = new JPanel(); //하단 컨테이너
	
	  // 1. 컴포넌트들을 만들어 보자.
	  // 상단 컨테이너
	   JLabel jl1 = new JLabel("결제수단 선택하세요");
	   
	  // 하단 컨테이너
	   JButton button1 = new JButton("카드 결제");
	   JButton button2 = new JButton("현금 결제");
	   JButton button3 = new JButton("취   소");
	   
	   
	  // 2. 컴포넌트를 컨테이너에 올려야 한다.
	   container1.add(jl1);
	   container2.add(button1);
	   container2.add(button2);
	   container2.add(button3);
	   
	  // 3. 컨테이너를 프레임에 올려야 한다.
	   add(container1, BorderLayout.NORTH);
	   add(container2, BorderLayout.SOUTH);
	   
	   setBounds(300,300,400, 250);
	   
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   setVisible(true);
	   
	   button1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			new Card_02();
			dispose();
		}
	});
	   
	   button2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			new Cash_01();
			dispose();
		}
	});
	   
	   button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

			System.exit(0);
			}
		});
	   
	} 
	
	public static void main(String[] args) {
		
		new Counter();

	}

}
