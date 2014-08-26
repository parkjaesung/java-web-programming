package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
	private Connection conn;

	public JdbcTemplate(Connection conn) {
		this.conn = conn;
	}

	public void update(String query, PreparedStatementSetter pss)
			throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		pss.setValues(pstmt);

		pstmt.executeUpdate();
	}
	
	public void update(String query, Object... params)
			throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		for (int i = 0; i < params.length; i++) {
			pstmt.setObject(i+1, params[i]);
		}
		pstmt.executeUpdate();
	}
	
	public <T> T queryById(String sql, RowMapper<T> rowMapper) throws SQLException {
		List<T> result = query(sql, rowMapper);
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	public <T> T queryById(String sql, RowMapper<T> rowMapper,
			PreparedStatementSetter pss) throws SQLException {
		List<T> result = query(sql, rowMapper, pss);
		if (result.isEmpty()) {
			return null;
		}

		return result.get(0);
	}
	
	public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws SQLException {
		return query(sql, rowMapper, null);
	}

	public <T> List<T> query(String sql, RowMapper<T> rowMapper,
			PreparedStatementSetter pss) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		if (pss != null) {
			pss.setValues(pstmt);
		}

		ResultSet rs = pstmt.executeQuery();
		List<T> result = new ArrayList<T>();
		while (rs.next()) {
			result.add(rowMapper.mapRow(rs));
		}
		return result;
	}
}
