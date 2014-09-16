package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import next.model.Question;
import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;

@Repository
public class QuestionDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTempate) {
		this.jdbcTemplate = jdbcTempate;
	}

	public void insert(final Question question) throws SQLException {
		String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfComment) VALUES (?, ?, ?, ?, ?)";
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, question.getWriter());
				pstmt.setString(2, question.getTitle());
				pstmt.setString(3, question.getContents());
				pstmt.setTimestamp(4,
						new Timestamp(question.getTimeFromCreateDate()));
				pstmt.setInt(5, question.getCountOfComment());
			}
		};
		jdbcTemplate.update(sql, pss);
	}

	public List<Question> findAll() throws SQLException {
		String sql = "SELECT questionId, writer, title, createdDate, countOfComment FROM QUESTIONS "
				+ "order by questionId desc";

		RowMapper<Question> rowMapper = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"), null,
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfComment"));
			}
		};

		return jdbcTemplate.query(sql, rowMapper);
	}

	public Question findById(final long questionId) throws SQLException {
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfComment FROM QUESTIONS "
				+ "WHERE questionId = ?";

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, questionId);
			}
		};

		RowMapper<Question> rowMapper = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"),
						rs.getString("contents"),
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfComment"));
			}
		};

		return jdbcTemplate.queryById(sql, rowMapper, pss);
	}

	public void updateCommentCount(final long questionId) throws SQLException {
		String countplussql = "update QUESTIONS set countOfComment = countOfComment + 1 where questionId = ?";
		jdbcTemplate.update(countplussql, questionId);
	}
}
