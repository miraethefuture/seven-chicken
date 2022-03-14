package khie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class _E_Mileage extends JFrame{

	Connection con = null;                  // DB와 연결하는 객체
	PreparedStatement pstmt = null;         // SQL문을 DB에 전송하는 객체
	ResultSet rs = null;                    // SQL문 실행 결과를 가지고 있는 객체
	String sql = null;
	
	int Pm = 0;
	int price = 0;
	int newMileage = 0;   // findId 와 updateNewMileage() 메서드에서 사용
	String id = "";		  // findId 와 updateNewMileage() 메서드에서 사용
	
	public _E_Mileage() {
		
		connect();
		findId();
		
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
	
	
	void findId() {

		 id = "";
		 int mileage = 0;  			// DB에서 마일리지 가져와서 할당
		 int ordertotal = 0;        // DB에서 총 결제금액 가져와서 할당
		 int mil = 0;				// 사용할 마일리지 입력 받아서 할당

		 connect();

		 try {

		   sql = "select login_id from login_info where login_date = (select max(login_date) from login_info)";
		   pstmt = con.prepareStatement(sql);
		   rs = pstmt.executeQuery();

		   while(rs.next()) {

		     // 가장 최근에 로그인한 아이디 값 가져오기
		       id = rs.getString("login_id");

		       // 비회원 주문 버튼을 클린한 경우 "비회원"이 DB에 전달됨
		       if(id.equals("비회원")) {

		         JOptionPane.showMessageDialog(null, "비회원은 마일리지를 사용하실 수 없습니다.");

		         // "비회원"이 아니라면 위에서 가져온 id 값을 이용 -> membertable에서 mem_point(마일리지)를 가져옴.
		       } else {

		        String getMemPoint = "select mem_point from membertable where mem_id = ?";
		        pstmt = con.prepareStatement(getMemPoint);
		        pstmt.setString(1, id);
		        rs = pstmt.executeQuery();

		        while(rs.next()) {

		        mileage = rs.getInt("mem_point");

		        } // while문 end

		        int result = JOptionPane.showConfirmDialog(null, "보유하신 마일리지는 [ " + mileage + " ] 입니다. 사용하시겠습니까?", "마일리지 사용", JOptionPane.YES_NO_OPTION);

		        if(result == JOptionPane.YES_OPTION) {

		          // 다이얼로그로 사용할 만큼의 마일리지 입력 받기
		          String mileageAmount = JOptionPane.showInputDialog("사용할 마일리지를 입력하세요. ");
		          mil = Integer.parseInt(mileageAmount); 	// 입력 받은 값을 정수로 변환하여 할

		          // 사용 원하는 마일리지 > 보유 마일리지 ->경고창 
		          // else -> 마일리지 사용 프로세스 작동
		          
		          if(mil > mileage) {
		        	  JOptionPane.showMessageDialog(null, "마일리지가 부족합니다.");
		          } else if(mil > 0) {
		        	// 총 결제가격 가져오기
			          String getOrderTotal = "select * from ordertable where order_date = (select max(order_date) from ordertable)";
			          pstmt = con.prepareStatement(getOrderTotal);
			          rs = pstmt.executeQuery();

			          while(rs.next()) {

			            ordertotal= rs.getInt("order_total");
			          }

			          price = (ordertotal - mil);	// 마일리지 사용 후 총 결제 금액
			          updateTotalPrice();
			          
			          
			          newMileage = (mileage - mil);		// 사용하고 남은 마일리지
			          updateNewMileage();
		        	  
		          }




		        } else if(result == JOptionPane.NO_OPTION) {
		        JOptionPane.showMessageDialog(null, "마일리지 사용 취소");

		      }



		      }
		     }

		   // 1. 계산 버튼 클릭 시 계산 되도록
		   // 2. 거스름 돈 화면에 보이는지
		   // 3. 변경된 마일리지 금액 데이터 베이스에 저장

		 } catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		}

		} // findId 메서드
	
	
		// 마일리지 사용 후 금액을 DB로 전송하는 메서드
		void updateTotalPrice() {
			
			try {
				  sql = "update ordertable set order_total = ? where order_date = (select max(order_date) from ordertable)";
				  pstmt = con.prepareStatement(sql);

				  pstmt.setInt(1, price); 		// 전역 변수 사용

				  int res = pstmt.executeUpdate();

				  if(res > 0) {
				    JOptionPane.showMessageDialog(null, "마일리지 사용 성공");
				  } else {
				    JOptionPane.showMessageDialog(null, "마일리지 사용 실패");
				  }

				  pstmt.close();


				} catch (SQLException e) {
				  e.printStackTrace();
				}

			
			
		}

		// 사용하고 남은 마일리지 DB에 전송하는 메서드
		void updateNewMileage() {

		  try {
		  sql = "update membertable set mem_point = ? where mem_id = ?";
		  pstmt = con.prepareStatement(sql);

		  pstmt.setInt(1, newMileage); 		// 전역 변수 사용
		  pstmt.setString(2, id); 			// 전역 변수 사용

		  int res = pstmt.executeUpdate();

		  if(res > 0) {
		    JOptionPane.showMessageDialog(null, "남은 사용가능한 마일리지는 [ " + newMileage + " ] 입니다.");
		  } else {
		    JOptionPane.showMessageDialog(null, "업데이트 실패");
		  }

		  pstmt.close();


		} catch (SQLException e) {
		  e.printStackTrace();
		}



		} // updateNewMileage() end
	
	
	
	

	
	
	//	public static void main(String[] args) {
//		
//      new _E_Mileage();
//	}

}
