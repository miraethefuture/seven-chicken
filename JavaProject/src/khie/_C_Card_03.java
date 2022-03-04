package khie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class _C_Card_03 extends JFrame{ 
	
	public _C_Card_03() {
		
		
		setTitle("결제 완료");
		
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();

		JLabel jl1 = new JLabel("          ");
		JLabel jl2 = new JLabel("카드 결제가 완료되었습니다.");
		
		JButton button1 = new JButton("확인");
		
		container1.add(jl1);
		container1.add(jl2);
		container2.add(button1);
		
		add(container1, BorderLayout.NORTH);
		add(container2, BorderLayout.CENTER);
		add(container3, BorderLayout.SOUTH);	
		
		setBounds(300,300,400, 250);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   
		setVisible(true);
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new _E_Mileage();
				dispose();
				
			}
		});
	}

	public static void main(String[] args) {
		
		new _C_Card_03();

	}

}
