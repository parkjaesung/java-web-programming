package next.support.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionManager {
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_URL = "jdbc:h2:~/next-qna";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PW = "";
	
	public static DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DB_DRIVER);
		ds.setUrl(DB_URL);
		ds.setUsername(DB_USERNAME);
		ds.setPassword(DB_PW);
		return ds;
	}
	
	public static Connection getConnection() {
		try {
			return getDataSource().getConnection();
		} catch (SQLException e) {
		}
		return null;
	}
}
