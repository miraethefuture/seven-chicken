package khie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import khie._E_Mileage;

public class _C_Card_03 extends JFrame{ 
	
	public _C_Card_03() {
		
		
		setTitle("결제 완료");
		
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container0 = new JPanel();

		//JLabel jl1 = new JLabel("          ");
		JLabel jl2 = new JLabel("카드 결제가 완료되었습니다.");
		
		JButton button1 = new JButton("확인");
		
		//container0.add(jl1);
		container1.add(jl2);
		container2.add(button1);
		
		//add(container0, BorderLayout.NORTH);
		add(container1, BorderLayout.CENTER);
		add(container2, BorderLayout.SOUTH);
		
		setBounds(300,300,280,130);
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   
		setVisible(true);
		
		// 확인 버튼
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//new Mileage();
				dispose();
				
			}
		});
	}

}
