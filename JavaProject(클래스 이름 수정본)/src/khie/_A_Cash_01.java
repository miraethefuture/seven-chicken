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
    int price = 0;
	
	
    // 기본 생성자
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
				
					findId();
				
			
				
				
				
				
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
    
    
    // 인자 생성자 
    public _A_Cash_01(int resultPrice) {

		setTitle("현금 결제");

		// 컨테이너를 만들어야 한다.
		JPanel container0 = new JPanel();
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();

		// 1. 컴포넌트를 만들어야 한다.
		// 1-1. 상단에 들어갈 컴포넌트
     	connect();
		getResultPrice();
		
		this.resultPrice = resultPrice;
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
				
					findId();
				
			
				
				
				
				
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

	// DB를 연동하는 메서드
	public void connect() {
		
		String driver = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@192.168.0.4:1521:xe";

		String user = "web";

		String password = "1234";
		
		try {
			// 1. 접속할 오라클 데이터베이스 드라이버를 메모리에 올리자. - 동적 작업
			Class.forName(driver);
			
			// 2. 오라클 데이터베이스와 연결 시도
			con = DriverManager.getConnection(url, user, password);
			
//			if(con != null) {
//				JOptionPane.showMessageDialog(null, "DB 연결 성공");
//			} else {
//				JOptionPane.showMessageDialog(null, "DB 연결 실패");
//			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	} // connect() 메소드 end
	
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
 	
 	
    int findId() {
   	 
   	 String id = "";
   	 int mileage = 0;
   	 int ordertotal = 0;
   	int mil = 0;
   	 
   	 
   	 connect();
   	 
   	 sql = "select login_id from login_info where login_no = (select max(login_no) from login_info)";
   	 
   	 try {
			
   		 pstmt = con.prepareStatement(sql);
   		 rs = pstmt.executeQuery();
   		 
   		 while(rs.next()) {
   			 
       		 id = rs.getString("login_id");
       		 
       		 if(id.equals("비회원")) {
       			 
       			 JOptionPane.showMessageDialog(null, "비회원은 마일리지를 사용하실 수 없습니다.");
       			 
       			 
       			
       			
       		} else {
       			 
       			String sqlMemPoint = "select mem_point from membertable where mem_id = ?";
       			pstmt = con.prepareStatement(sqlMemPoint);
       			pstmt.setString(1, id);
       			rs = pstmt.executeQuery();
       			
       			while(rs.next()) {
       				
       				mileage = rs.getInt("mem_point");
       				
       			} // while 문 end
       			
       			int result = JOptionPane.showConfirmDialog(null, "보유하신 마일리지는 [ " + mileage + " ] 입니다. 사용하시겠습니까?", "마일리지 사용", JOptionPane.YES_NO_OPTION);
       
       			if(result == JOptionPane.YES_OPTION) {
   					
       				// 총 금액에서 마일리지를 뺀 금액을 현금결제 창에 띄우기
       				// 얼마를 사용해야할지 입력하는 란 만들기
       				// 데이터 베이스에서 총 금액 가져오고 
       				// 총 금액 - 선택한 만큼의 마일리지 입력
       				
       				String mileageAmount = JOptionPane.showInputDialog("사용할 마일리지를 입력하세요. ");
       				JOptionPane.showMessageDialog(null, "사용할 마일리지는 [ " + mileageAmount + " ] 입니다.");
       				mil = Integer.parseInt(mileageAmount);
       				
       				String sqlOrderTotal = "select * from ordertable where order_num = (select max(order_num) from ordertable)";
       				
       				pstmt = con.prepareStatement(sqlOrderTotal);
       				rs = pstmt.executeQuery();
       				
       				while(rs.next()) {
       					
       					ordertotal = Integer.parseInt(rs.getString("order_total"));
       				}
       				
   				} else if(result == JOptionPane.NO_OPTION) {
   					JOptionPane.showMessageDialog(null, "마일리지 사용 취소");
   				} 
       		
       			price = (ordertotal - mil);
       			new _A_Cash_01(price);
       		
       		}
       	 } 
   		 
   	 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mileage;
   } // findId 메서드
 	
     public static void main(String[] args) {
		
    	 new _A_Cash_01();
	}
     
}

