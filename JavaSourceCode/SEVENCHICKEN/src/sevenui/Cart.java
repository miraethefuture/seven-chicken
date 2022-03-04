package sevenui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Cart extends JFrame{
	
	DecimalFormat formatter = new DecimalFormat("###,###");
	
	Connection con = null;				// DB와 연결하는 객체
	PreparedStatement pstmt = null;		// SQL문을 DB에 전송하는 객체
	ResultSet rs = null;				// SQL문 실행 결과를 가지고 있는 객체
	String sql = null;					// SQL문을 저장하는 문자열 변수.
	
	DefaultTableModel model;
	JTable table;
	JLabel resultPrice;
	int result = Menu.result;
	
	public Cart() {
		
		setTitle("담은 목록");
		
		JPanel container = new JPanel();
		
		Font font = new Font("맑은 고딕", Font.BOLD, 16);
		
		// 장바구니 화면 중앙
		String[] header = 
			{"메  뉴  명", "가  격", "수  량"};
			
		model = new DefaultTableModel(header, 0);
		
		table = new JTable(model);
		
		JScrollPane jsp = new JScrollPane
				(table,
				 ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				 ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// 하단 버튼, 총 금액
		JButton plusBtn = new JButton("+");
		JLabel emptyjlb = new JLabel("        ");
		JButton minusBtn = new JButton("-");
		JLabel resultPriceText = new JLabel("        총 금액 : ");
		resultPrice = new JLabel(formatter.format(Menu.result) + "원");
		JButton orderBtn = new JButton("결제하기");
		
		plusBtn.setFont(font);
		minusBtn.setFont(font);
		
		// 컴포넌트 올리기
		container.add(plusBtn);
		container.add(emptyjlb);
		container.add(minusBtn);
		container.add(resultPriceText);
		container.add(resultPrice);
		container.add(orderBtn);
		
		// 프레임에 올리기
		add(jsp, BorderLayout.CENTER);
		add(container, BorderLayout.SOUTH);
		
		// 창 설정
		setBounds(300,300,400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// 선택한 메뉴 목록
		connect();
		model.setRowCount(0);
		select();
		
	// 이벤트 처리
		// 수량 추가
		plusBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connect();
				plus();
				model.setRowCount(0);	// 전체 테이블 화면을 지워주는 메서드
				select();
			}
		});
		
		// 수량 감소
		minusBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connect();
				minus();
				model.setRowCount(0);	// 전체 테이블 화면을 지워주는 메서드
				select();
			}
		});
		
		// 결제 버튼
		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// DB에 result 값 넘겨주기
				
				
				new Pay();	// 결제 화면
			}
		});
		
	}	// 생성자 end

	// DB를 연동하는 메서드
	public void connect() {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";
		
		try {
			// 1. 접속할 오라클 데이터베이스 드라이버를 메모리에 올리자. - 동적 작업
			Class.forName(driver);
			
			// 2. 오라클 데이터베이스와 연결 시도
			con = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	} // connect() 메소드 end

	// 선택한 메뉴 목록 불러오기
	public void select() {
		
		try {
			// 1. 오라클 데이터베이스로 전송할 SQL문 작성
			sql = "select * from menutable where menu_count > 0 order by menu_name";
			
			pstmt = con.prepareStatement(sql);
			
			// 2. 오라클 데이터베이스에 SQL문 전송 및 SQL문 실행.
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String menu_name = rs.getString("menu_name");
				int menu_price = rs.getInt("menu_price");
				int menu_count = rs.getInt("menu_count");
				
				Object[] data = 
					{menu_name, menu_price, menu_count};
				
				// 저장된 한 개의 레코드(데이터)를 model에 추가.
				model.addRow(data);
			}
			
			// 3. 오라클 데이터베이스에 연결되어 있던 자원 종료
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}  // select() 메서드 end
	
	public void plus() {

		int row = table.getSelectedRow();
		
		try {
			sql = "update menutable set menu_count = ? where menu_name = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, ((int)model.getValueAt(row, 2)) + 1);
			pstmt.setString(2, (String)model.getValueAt(row, 0));
			
			int res = pstmt.executeUpdate();
			
			pstmt.close(); 	// con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		int price = (int)model.getValueAt(row, 1);	// 품목 가격
		result = result + price;
		resultPrice.setText(formatter.format(result) + "원");
		
		
	}	// plus() 메서드 end
	
	public void minus() {
		
		int row = table.getSelectedRow();
		
		if((int)model.getValueAt(row, 2) <= 0) {
			JOptionPane.showMessageDialog(null, "품목 수량이 0입니다.");
			
		} else {
			try {
				sql = "update menutable set menu_count = ? where menu_name = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, ((int)model.getValueAt(row, 2)) - 1);
				pstmt.setString(2, (String)model.getValueAt(row, 0));
				
				int res = pstmt.executeUpdate();
				
				pstmt.close(); 	// con.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			int price = (int)model.getValueAt(row, 1);	// 품목 가격
			result = result - price;
			resultPrice.setText(formatter.format(result) + "원");
		}
	}	// minus() 메서드 end
}