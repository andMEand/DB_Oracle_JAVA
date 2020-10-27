import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PRE_SelectExam {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con = null;
		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "Select*From member order by hakbun";
		try {
			Class.forName(driver); //예외 안에~
			con = DriverManager.getConnection(url,"scott","123456");//예외 안에~
		
			//stmt = con.createStatement( );//예외 안에~
			stmt = con.prepareStatement(sql);
		
			//rs = stmt.executeQuery(sql);
			rs = stmt.executeQuery();
		
			System.out.println("hekbun\t name\taddr\tphone");
			System.out.println("--------------------------------");
			while(rs.next()) {
				System.out.print(rs.getString("hakbun")+"\t");
				System.out.print(rs.getString("name")+"\t");
				System.out.print(rs.getString("addr")+"\t");
				System.out.print(rs.getString("phone")+"\n");
			}
			
		}
		catch(Exception e) {
			System.out.println("데이터 베이스 연결 실패!");
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null)con.close();
				
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
