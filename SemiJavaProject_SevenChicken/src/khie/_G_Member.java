package khie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class _G_Member extends JFrame {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	String sql = null;
	
	DefaultTableModel model;
	
	JTextField jtf1;
	JTextField jtf2;
	JPasswordField jtf3;
	JTextField jtf4;
	JTextField jtf5;
	JTextField jtf6;

	
	JTable table;
	
	int row;
	
	public _G_Member() {
		
		setTitle("회원 관리");
		
		// 컨테이너
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();
		
		// 컴포넌트
		// 상단 부분 라벨과 텍스트 필드
//		JLabel jlb1 = new JLabel("회원 번호 : "); 시퀀스 넘버 추가로 주석 처리
//		jtf1 = new JTextField(10);
		
		JLabel jlb1 = new JLabel("회원 이름 : ");
		jtf1 = new JTextField(7);
		
		JLabel jlb2 = new JLabel("아이디 : ");
		jtf2 = new JTextField(10);
		
		JLabel jlb3 = new JLabel("비밀번호 : ");
//		jtf4 = new JTextField(10);
		jtf3 = new JPasswordField(10);
		
		JLabel jlb4 = new JLabel("포인트 : ");
		jtf4 = new JTextField(10);
		
		JLabel jlb5 = new JLabel("주 소 : ");
		jtf5 = new JTextField(20);
		
		JLabel jlb6 = new JLabel("연락처 : ");
		jtf6 = new JTextField(15);
		
		
		// text area
		
		String[] header = {"주문번호", "회원 이름", "아이디", "비밀번호", "포인트", "주 소", "연락처"};
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		
		JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JButton jb1 = new JButton("전체 목록");
		JButton jb2 = new JButton("회원 등록");
		JButton jb3 = new JButton("회원 수정");
		JButton jb4 = new JButton("회원 삭제");
		JButton jb5 = new JButton("관리자 메인화면");
		
		
		
		// 컨테이너에 올리기 
		container1.add(jlb1); container1.add(jtf1);
		container1.add(jlb2); container1.add(jtf2);
		container1.add(jlb3); container1.add(jtf3);
		container1.add(jlb4); container1.add(jtf4);
		
		container2.add(jlb5); container2.add(jtf5);
		container2.add(jlb6); container2.add(jtf6);
//		container2.add(jlb7); container2.add(jtf7);
		
		container3.add(jb1);
		container3.add(jb2);
		container3.add(jb3);
		container3.add(jb4);
		container3.add(jb5);
		
		// 새로운 컨테이너 위에 기존의 컨테이너들 올리기
		JPanel group = new JPanel(new BorderLayout());
		
		group.add(container2, BorderLayout.NORTH);
		group.add(jsp, BorderLayout.CENTER);
		group.add(container3, BorderLayout.SOUTH);
		
		// 3. 컨테이너 on 프레임 
		add(container1, BorderLayout.NORTH);
		add(group, BorderLayout.CENTER);
	
		
		setBounds(200, 200, 800, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// 이벤트 추가 
		// 전체 목록 
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connect();
				model.setRowCount(0); // 테이블 화면 초기화 
				select();
				
			}
		});
		
		// 회원 등록
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connect();
				insert();
				jtf1.setText("");
				jtf2.setText("");
				jtf3.setText("");
				jtf4.setText("");
				jtf5.setText("");
				jtf6.setText("");
				
				
				jtf1.requestFocus();
				model.setRowCount(0); 
			
				select();
				
			}
		});
		
		// 회원 수정
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connect();
				update();
				
				jtf1.setText("");
				jtf2.setText("");
				jtf3.setText("");
				jtf4.setText("");
				jtf5.setText("");
				jtf6.setText("");
			
				
				jtf1.requestFocus();
				model.setRowCount(0); 
			
				select();
				
			}
		});
		
		// 회원 삭제
		jb4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?",
						"확인", JOptionPane.YES_NO_OPTION); 
				
				if(result == JOptionPane.CLOSED_OPTION) { // no 버튼 클릭시 같은 값 반환
					JOptionPane.showMessageDialog(null, "취소를 클릭하셨습니다.");
					
				} else if(result == JOptionPane.YES_OPTION) {
					connect();
					delete();
					
					jtf1.setText("");
					jtf2.setText("");
					jtf3.setText("");
					jtf4.setText("");
					jtf5.setText("");
					jtf6.setText("");
					
				
				}
			}
		});
		
		jb5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new _F_Admin();
				dispose();
				
			}
		});
		
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = table.getSelectedRow();
				jtf1.setText(model.getValueAt(row, 1).toString());
				jtf2.setText(model.getValueAt(row, 2).toString());
				jtf3.setText(model.getValueAt(row, 3).toString());
				jtf4.setText(model.getValueAt(row, 4).toString());
				jtf5.setText(model.getValueAt(row, 5).toString());
				jtf6.setText(model.getValueAt(row, 6).toString());
			
				
			}
		});
		
		
		
		
		
		
		
		
		
		
	} // 생성자 end 
	
	
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
	
	
	// select() 메서드
	void select() {
		
		try {
			
			sql = "select * from membertable order by mem_no"; 
			
			// SQL 문을 전송하자. 
			pstmt = con.prepareStatement(sql);
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 데이터 출력 
			while(rs.next()) {
				
				int memNo = rs.getInt("mem_no");
				String memName = rs.getString("mem_name");
				String memId = rs.getString("mem_id");
				String memPwd = rs.getString("mem_pwd");
				int memPoint = rs.getInt("mem_point");
				String memAddr = rs.getString("mem_addr");
				String memPhone = rs.getString("mem_phone");
				
						
				Object[] data = {memNo, memName, memId, memPwd, memPoint, memAddr, memPhone};
				
				// 저장한 한개의 레코드를 model에 추가
				model.addRow(data);
				
				
			}
			
			rs.close(); pstmt.close(); con.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	} //select() end
	
	//insert() 시작 
	void insert() {
		
		try {
		
			sql = "insert into membertable values(member_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
//			pstmt.setInt(1, Integer.parseInt(jtf1.getText()));
			pstmt.setString(1, jtf1.getText());
			pstmt.setString(2, jtf2.getText());
			pstmt.setString(3, jtf3.getText());
			pstmt.setInt(4, Integer.parseInt(jtf4.getText())); 
			pstmt.setString(5, jtf5.getText());
			pstmt.setString(6, jtf6.getText());
			
			
			int res = pstmt.executeUpdate();
			
			if(res > 0) {
				
				JOptionPane.showMessageDialog(null, "회원 등록 성공");
				
			} else {
				JOptionPane.showMessageDialog(null, "회원 등록 실패");
				
			}
			
			pstmt.close(); 
			
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	} // insert() end
	
	// 메뉴 수정 (마우스 리스너 사용해야 할 듯)
	void update() {
		
		
		try {
			sql = "update membertable set mem_name = ?, mem_id = ?, mem_pwd = ?, mem_point = ?, mem_addr = ?, mem_phone = ? where mem_name = ?"; 
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, jtf1.getText());
			pstmt.setString(2, jtf2.getText());
			pstmt.setString(3, jtf3.getText());
			pstmt.setInt(4, Integer.parseInt(jtf4.getText())); 
			pstmt.setString(5, jtf5.getText());
			pstmt.setString(6, jtf6.getText());
			
			int row = table.getSelectedRow(); 
			pstmt.setString(7, (String)model.getValueAt(row, 1)); 
			
			
			int res = pstmt.executeUpdate();
			
			if(res > 0) {
				JOptionPane.showMessageDialog(null, "회원 정보 변경 성공");
			} else {
				JOptionPane.showMessageDialog(null, "회원 정보 변경 실패");
			}
			
			pstmt.close(); 
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	} // update() end
	
	// delete() 시작
	void delete() {
		
		
		try {
			sql = "delete from membertable where mem_name = ?";
			pstmt = con.prepareStatement(sql);
			
			// 선택한 행 인덱스 번호가 반환됨
			int row = table.getSelectedRow(); // jtable 선택된 셀의 row 값을 int형으로 반환 
			
			pstmt.setString(1, (String)model.getValueAt(row, 1)); 
			
			int res = pstmt.executeUpdate();
			
			if(res > 0) {
				
				JOptionPane.showMessageDialog(null, "회원 삭제 성공");
			
			} else {
				
				JOptionPane.showMessageDialog(null, "회원 삭제 실패");
				
			}
			
			model.removeRow(row); // 테이블 상의 한 줄 삭제 
			pstmt.close(); con.close();
			
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // delete() end
	

//	public static void main(String[] args) {
//		
//		new _G_Member();
//	}

}