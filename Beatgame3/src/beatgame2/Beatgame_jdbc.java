package beatgame2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Beatgame_jdbc {

	private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver"; // Driver Ŭ���� Ǯ���� (JQFN)
	private final String DB_URL = "jdbc:mysql://localhost:3306/testDB?"
								+ "useUnicode=true"
								+ "&characterEncoding=utf8";
	private final String USERNAME = "root";
	private final String PASSWORD = "1234"; 
	private String name;
	private int score;

	int a=0;
	
	public void saveInfo(String name,int score) {
		this.name=name;
		this.score=score;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			ps = conn.prepareStatement("SELECT EXISTS (SELECT 1 FROM Information_schema.tables WHERE table_schema = 'testDB' AND table_name = 'Beat_Game') AS flag");		
			rs = ps.executeQuery();
			if(rs.next())
				a=rs.getInt("Flag");
			
			
			if(a==0)
			{
				sql ="CREATE TABLE Beat_Game(id INT AUTO_INCREMENT PRIMARY KEY,name VARCHAR(40),`score` INT)";
				ps=conn.prepareStatement(sql);
				ps.execute();
			}
		
			sql ="INSERT INTO Beat_Game(name,`score`) VALUES(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, score);
			
			ps.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) { ps.close(); }
				if(conn != null) { conn.close(); }
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public String getInfo()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getinfo="사용자\t\t"+"점수\n";
		
		String sql;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			ps = conn.prepareStatement("SELECT EXISTS (SELECT 1 FROM Information_schema.tables WHERE table_schema = 'testDB' AND table_name = 'Beat_Game') AS flag");		
			rs = ps.executeQuery();
			if(rs.next())
				a=rs.getInt("Flag");
			
			
			if(a==0)
			{
				return null;
			}
		
			ps = conn.prepareStatement("SELECT 	name,`score` FROM Beat_Game ORDER BY `score` DESC");		
			rs = ps.executeQuery();
			while(rs.next())
			{
				getinfo=getinfo+rs.getString(1)+"\t\t"+rs.getInt(2)+"\n";
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) { ps.close(); }
				if(conn != null) { conn.close(); }
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return getinfo;
	}
}
