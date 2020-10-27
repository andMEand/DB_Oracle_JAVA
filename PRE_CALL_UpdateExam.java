/*
create or replace procedure call_update (p_hakbun member.hakbun%type,
		p_addr member.addr%type, p_phone member.phone%type)
is
begin
update member set   addr = p_addr,
phone =p_phone
where hakbun =p_hakbun;
end;
/
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;



public class PRE_CALL_UpdateExam {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con = null;
		CallableStatement cstmt = null;
		
		String hakbun, addr, phone;
		
		try {
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			System.out.println("member 테이블에 값 갱신하기....");
			System.out.print("갱신할 학번 입력: ");
			hakbun = br.readLine();
			System.out.print("새 주소 입력: ");
			addr = br.readLine();
			System.out.print("새전화번호 입력:: ");
			phone =br.readLine();
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			
			cstmt = con.prepareCall("{call call_update(?,?,?)}");
			//디폴트(ㅑㅜ)
			cstmt.setString(1, hakbun);
			cstmt.setString(2, addr);
			cstmt.setString(3, phone);
			int res = cstmt.executeUpdate();
			
			System.out.println("데이터베이스 연결 성공!");
		}
		catch(Exception e) {
			System.out.println("데이터베이스 연결 실패!+"+e.getMessage());
			
		}
		finally {
			try {
				if(con != null)con.close();
				if(cstmt != null)cstmt.close();
				
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
