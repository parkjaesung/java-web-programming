package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import next.model.Answer;
import next.support.db.ConnectionManager;
import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;

public class AnswerDao {
	private JdbcTemplate jdbcTempate;

	public AnswerDao() {
		jdbcTempate = new JdbcTemplate(ConnectionManager.getConnection());
	}

	public void insert(final Answer answer) throws SQLException {
		String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, answer.getWriter());
				pstmt.setString(2, answer.getContents());
				pstmt.setTimestamp(3, new Timestamp(answer.getTimeFromCreateDate()));
				pstmt.setLong(4, answer.getQuestionId());
			}
		};
		
		jdbcTempate.update(sql, pss);

		String countplussql = "update QUESTIONS set countOfComment = countOfComment + 1 where questionId = ?";
		jdbcTempate.update(countplussql, answer.getQuestionId());
	}

	public List<Answer> findAllByQuestionId(final long questionId)
			throws SQLException {

		String sql = "SELECT answerId, writer, contents, createdDate FROM ANSWERS WHERE questionId = ? "
				+ "order by answerId desc";
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, questionId);
			}
		};
		
		RowMapper<Answer> rowMapper = new RowMapper<Answer>() {
			@Override
			public Answer mapRow(ResultSet rs) throws SQLException {
				return new Answer(rs.getLong("answerId"),
						rs.getString("writer"), rs.getString("contents"),
						rs.getTimestamp("createdDate"), questionId);
			}
			
		};

		return jdbcTempate.query(sql, rowMapper, pss);
	}
}
