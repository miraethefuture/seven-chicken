package sevenChicken;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mileage extends JFrame{

	public Mileage() {
	
		setTitle("마일리지 사용");
		
		JPanel container1 = new JPanel(); //상단 컨테이너
		JPanel container2 = new JPanel(); //하단 컨테이너
		JPanel container3 = new JPanel(); //하단 컨테이너
		JPanel container4 = new JPanel(); //하단 컨테이너
		
		JLabel jl1 = new JLabel("결제 금액 : ");
		JLabel jl2 = new JLabel("현재 마일리지 금액 : ");
		
		JButton button1 = new JButton("마일리지 사용");
		JButton button2 = new JButton("취   소");
		
		   container1.add(jl1);
		   container2.add(jl2);
		   container3.add(button1);
		   container3.add(button2);
		   
		   add(container1, BorderLayout.NORTH);
		   add(container2, BorderLayout.CENTER);
		   add(container3, BorderLayout.SOUTH);
		   
		   setBounds(300,300,300,300);
		   
		   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   
		   setVisible(true);
}
	
	public static void main(String[] args) {
		
		new Mileage();

	}

}
