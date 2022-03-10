package khie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import oracle.jdbc.driver.DBConversion;

public class _A_Cash_01 extends JFrame{
	
	DecimalFormat formatter = new DecimalFormat("###,###");
	
	Connection con = null;                  // DB와 연결하는 객체
	PreparedStatement pstmt = null;         // SQL문을 DB에 전송하는 객체
	ResultSet rs = null;                    // SQL문 실행 결과를 가지고 있는 객체
	String sql = null;

	DefaultTableModel model;
	JTable table;
	DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
	int resultPrice = 0;
    int Pm = 0;
	
	public _A_Cash_01() {

		setTitle("현금 결제");

		// 컨테이너를 만들어야 한다.
		JPanel container0 = new JPanel();
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();

		// 1. 컴포넌트를 만들어야 한다.
		// 1-1. 상단에 들어갈 컴포넌트
     	connect();
		getResultPrice();

		JLabel jl2 = new JLabel("결제 금액 : " + resultPrice);
		JLabel moneyText = new JLabel("입금액 : ");
		JTextField money = new JTextField(10);

		// 중앙 컴포넌트
		String[] header = 
			{"메  뉴  명", "가  격", "수  량"};

		model = new DefaultTableModel(header, 0);
		
		table = new JTable(model);
		
		// 테이블 셀 간격
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.setRowHeight(20);
		
		//table.setFont(font);
		table.setSelectionBackground(Color.WHITE);
		
		JScrollPane jsp = new JScrollPane(
				table,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getViewport().setBackground(Color.WHITE);
		
		// 테이블 가운데 정렬
		celAlignCenter.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tableCenter = table.getColumnModel();
		
		for (int i = 0; i < tableCenter.getColumnCount(); i++) {
			tableCenter.getColumn(i).setCellRenderer(celAlignCenter);
			}
		
		// 1-2. 하단에 들어갈  컴포넌트
		JButton button2 = new JButton("마일리지 사용");
		JButton button3 = new JButton("계산");
		JButton button4 = new JButton("취소");

		// 컴포넌트 올리기
		container0.add(jl2);
		container1.add(moneyText);	container1.add(money);
		container2.add(button2);
		container2.add(button3);	container2.add(button4);

		// 프레임에 올리기
		JPanel group = new JPanel(new BorderLayout());
		
		group.add(container1, BorderLayout.NORTH);
		group.add(jsp, BorderLayout.CENTER);
		group.add(container2, BorderLayout.SOUTH);

		add(container0, BorderLayout.NORTH);
		add(group, BorderLayout.CENTER);
		
		// 창 설정
		setBounds(300, 300, 400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// 선택한 메뉴 목록
		connect();
		model.setRowCount(0);
		select();


        // 마일리지 사용
        button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				int total = resultPrice;
//				int mileage = Pm;
//				int Nmileage = resultPrice - Pm;
//				int Pmileage = Nmileage/100;
//				
//				connect();
//				getResultPrice();		
				new _E_Mileage();
				connect();
				getResultPrice();
				dispose();
				new _B_Card_02();
				
			}
		});

        // 계산
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

					if(resultPrice > Integer.parseInt(money.getText())) {
						JOptionPane.showMessageDialog(null, "입금액이 부족합니다.");
					} else {
						int result = JOptionPane.showConfirmDialog(null, "결제하시겠습니까?",
								"확인", JOptionPane.YES_NO_OPTION);
						
						if(result == JOptionPane.NO_OPTION) {
							JOptionPane.showMessageDialog(null, "취소하셨습니다.");
						} else if(result == JOptionPane.YES_OPTION) {
							
							connect();
							updatePaid();
							updateMenuInven();
							new _D_Money_01();
							dispose();
						}
					}
			}
		});
		
		// 취소
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new _9_Counter();
				dispose();
			
			}
		});
	}

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
	
	// 결제금액을 가져오는 메서드
     void getResultPrice() {

	    try {

	    	sql = "select order_total from ordertable where order_date = (select max(order_date) from ordertable)";

		     pstmt = con.prepareStatement(sql);	

		     rs = pstmt.executeQuery();

		    while(rs.next()) {

		    	resultPrice = rs.getInt("order_total");

		        }
		    
		    
		      rs.close(); pstmt.close(); con.close();

	    } catch (SQLException e) {
		     
		       e.printStackTrace();
	          }
     } // getResultPrice() 메서드 end
     
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

 	// '대기중' -> '결제완료' 메서드
 	void updatePaid() {
 		
		try {
			sql = "update ordertable set paid = '결제완료' where order_date = (select max(order_date) from ordertable)";
			
			pstmt = con.prepareStatement(sql);
			
			int res = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
 	}	// updatePaid() 메서드 end
 	
 	
 	
 	// 결제 성공 시 재고 수량 감소
 	void updateMenuInven() {
 		
		try {
			sql = "merge into menu_inven i using menutable t on (i.menu_name = t.menu_name) when matched then update set i.menu_count = (i.menu_count - t.menu_count)";
			
			pstmt = con.prepareStatement(sql);
			
			int res = pstmt.executeUpdate();
			
			pstmt.close(); //con.close();
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
 	}	// updateMenuInven() 메서드 end
 	
 	
//     public static void main(String[] args) {
//		
//    	 new _A_Cash_01();
//	}
     
}
