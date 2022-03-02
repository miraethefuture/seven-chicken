package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;


public class Product extends JFrame {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	String sql = null;
	
	DefaultTableModel model;
	
	public Product() {
		
		setTitle("재고 관리");
		
		// 컨테이너
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		
		// 컴포넌트
		// 상단 부분 라벨과 텍스트 필드
		JLabel jlb1 = new JLabel("메뉴명 : ");
		JTextField jtf1 = new JTextField(5);
		
		JLabel jlb2 = new JLabel("가 격 : ");
		JTextField jtf2 = new JTextField(5);
		
		JLabel jlb3 = new JLabel("재고 수량 : ");
		JTextField jtf3 = new JTextField(5);
		
		
		// text area
		
		String[] header = {"메뉴명", "가 격", "재고 수량"};
		model = new DefaultTableModel(header, 0);
		JTable table = new JTable(model);
		
		JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JButton jb1 = new JButton("전체 목록");
		JButton jb2 = new JButton("메뉴 등록");
		JButton jb3 = new JButton("메뉴 수정");
		JButton jb4 = new JButton("메뉴 삭제");
		
		// 컨테이너에 올리기 
		container1.add(jlb1); container1.add(jtf1);
		container1.add(jlb2); container1.add(jtf2);
		container1.add(jlb3); container1.add(jtf3);
		
		container2.add(jb1);
		container2.add(jb2);
		container2.add(jb3);
		container2.add(jb4);
		
		add(container1, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(container2, BorderLayout.SOUTH);
		
		
		setBounds(200, 200, 500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// 이벤트 추가 
		
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connect();
				model.setRowCount(0); // 테이블 화면 초기화 
				select();
				
			}
		});
		
		
		
		
		
	} // 생성자 end 
	
	
	// connect() 메서드 : 후에 DB url, userid, pwd 변경해야 함.
	void connect() {
		
		String driver = "oracle.jdbc.OracleDriver";
		String url ="jdbc:oracle:thin:@db202202171628_high?TNS_ADMIN=/Users/mirae/Downloads/Wallet_DB202202171628";
        String userid="admin";
        String pwd ="Happari13121312";
        
        // 1. 오라클 드라이버 메모리에 올리기 
        try {
			Class.forName(driver);
		
		// 2. 오라클 DB와 연결 
			con = DriverManager.getConnection(url, userid, pwd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    } // connect() end
	
	
	// select() 메서드
	void select() {
		
		try {
			
			sql = "select * from menutable"; 
			
			// SQL 문을 전송하자. 
			pstmt = con.prepareStatement(sql);
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 데이터 출력 
			while(rs.next()) {
				
				String menuName = rs.getString("menu_name");
				int menuPrice = rs.getInt("menu_price");
				int menuCount = rs.getInt("menu_count");
						
				Object[] data = {menuName, menuPrice, menuCount};
				
				// 저장한 한개의 레코드를 model에 추가
				model.addRow(data);
				
				
			}
			
			rs.close(); pstmt.close(); con.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	} //select() end
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		new Product();
		
	}
	
	
}
