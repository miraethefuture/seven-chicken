package khie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class _7_Cart extends JFrame{
	
	DecimalFormat formatter = new DecimalFormat("###,###");
	
	Connection con = null;				// DB와 연결하는 객체
	PreparedStatement pstmt = null;		// SQL문을 DB에 전송하는 객체
	ResultSet rs = null;				// SQL문 실행 결과를 가지고 있는 객체
	String sql = null;					// SQL문을 저장하는 문자열 변수.
	
	DefaultTableModel model;
	JTable table;
	DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
	
	JLabel resultPrice;
	int result = _6_Menu.result;
	
	public _7_Cart() {
		
		setTitle("담은 목록");
		
		JPanel container = new JPanel();
		
		Font font = new Font("함초롬돋움", Font.BOLD, 12);
		Font fontPlusMinus = new Font("맑은 고딕", Font.BOLD, 16);
		
		// 장바구니 화면 중앙
		String[] header = 
			{"메  뉴  명", "가  격", "수  량"};
			
		model = new DefaultTableModel(header, 0);
		
		table = new JTable(model);
		
		// 테이블 셀 간격
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.setRowHeight(25);
		
		table.setFont(font);
		table.setSelectionBackground(Color.ORANGE);
		
		JScrollPane jsp = new JScrollPane
				(table,
				 ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				 ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getViewport().setBackground(Color.ORANGE);
		
		// 테이블 가운데 정렬
		celAlignCenter.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tableCenter = table.getColumnModel();
		
		for (int i = 0; i < tableCenter.getColumnCount(); i++) {
			tableCenter.getColumn(i).setCellRenderer(celAlignCenter);
			}
		
		// 하단 버튼, 총 금액
		JButton plusBtn = new JButton("+");
		JLabel emptyjlb = new JLabel("   ");
		JButton minusBtn = new JButton("-");
		JLabel resultPriceText = new JLabel("    총 금액 : ");
		resultPrice = new JLabel(formatter.format(result) + "원");
		JLabel emptyjlb2 = new JLabel("   ");
		JLabel emptyjlb3 = new JLabel("  ");
		JButton orderBtn = new JButton("결제하기");
		JButton toMenuBtn = new JButton("돌아가기");
		
		plusBtn.setFont(fontPlusMinus);
		minusBtn.setFont(fontPlusMinus);
		plusBtn.setBackground(Color.WHITE);
		minusBtn.setBackground(Color.WHITE);
		
		resultPriceText.setFont(font);
		resultPrice.setFont(font);
		orderBtn.setFont(font);
		orderBtn.setBackground(Color.WHITE);
		toMenuBtn.setFont(font);
		toMenuBtn.setBackground(Color.WHITE);
		
		// 컴포넌트 올리기
		container.add(plusBtn);
		container.add(emptyjlb);
		container.add(minusBtn);
		container.add(resultPriceText);
		container.add(resultPrice);
		container.add(emptyjlb2);
		container.add(orderBtn);
		container.add(emptyjlb3);
		container.add(toMenuBtn);
		container.setBackground(Color.WHITE);

		
		// 프레임에 올리기
		add(jsp, BorderLayout.CENTER);
		add(container, BorderLayout.SOUTH);
		
		// 창 설정
		setBounds(300,300,500,400);
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
				int i = table.getSelectedRow();
				model.setRowCount(0);	// 전체 테이블 화면을 지워주는 메서드
				select();
				table.setRowSelectionInterval(i, i);
			}
		});
		
		// 수량 감소
		minusBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connect();
				minus();
				int i = table.getSelectedRow();
				model.setRowCount(0);	// 전체 테이블 화면을 지워주는 메서드
				select();
				table.setRowSelectionInterval(i, i);
			}
		});
		
		// 결제 버튼
		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// DB에 result 값 넘겨주기
				connect();
				cartResultToDB();
				new _9_Counter();	// 결제 화면
			}
		});
		
		// 메뉴로 돌아가기 버튼
		toMenuBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				new _6_Menu();
			}
		});
		
	}	// 생성자 end

	// 민초님 connect()
	void connect() {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.0.4:1521:xe";
		String user = "web";
		String password = "1234";

		try {
			// 1. 접속할 오라클 데이터베이스 드라이버를 메모리에 올리자
			Class.forName(driver);

			// 2. 오라클 데이터베이스와 연결을 시도
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	// connect() 메서드 end

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
		_6_Menu.result = this.result;
		_2_Log.result = _6_Menu.result;
		_6_Menu.count++;
		_2_Log.count = _6_Menu.count;
		
		
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
			_6_Menu.result = this.result;
			_2_Log.result = _6_Menu.result;
			_6_Menu.count--;
			_2_Log.count = _6_Menu.count;
		
		}
	}	// minus() 메서드 end
	
	// 결과값을 DB로 넘기는 메서드
	public void cartResultToDB() {

		try {
			// 1. 오라클 데이터베이스에 전송할 SQL문 작성.
			sql = "insert into ordertable values(order_seq.nextval, sysdate || ' ' || TO_CHAR(SYSDATE, 'HH24:MI:SS'), ?, '대기중')";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, result);

			// 2. 오라클 데이터베이스에 SQL문 전송 및 SQL문 실행.
			int res = pstmt.executeUpdate();
			
			if(res > 0) {
				JOptionPane.showMessageDialog(null, "결과 전송 성공");
			} else {
				JOptionPane.showMessageDialog(null, "결과 전송 실패");
			}
			
			// 3. 오라클 데이터베이스에 연결되어 있던 자원 종료.
			pstmt.close();  con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
