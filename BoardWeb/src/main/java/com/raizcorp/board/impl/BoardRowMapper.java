package com.raizcorp.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.raizcorp.board.BoardVO;

// RowMapper 객체를 queryForObject() 메소드의 매개변수로 넘겨주면
// 스프링 컨테이너는 SQL 구문을 수행한 후 자동으로 RowMapper 객체의 mapRow() 메소드를 호출한다.
public class BoardRowMapper implements RowMapper<BoardVO> {

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO board = new BoardVO();
		
		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setWriter(rs.getString("WRITER"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDate(rs.getString("REGDATE"));
		board.setCnt(rs.getInt("CNT"));

		return board;
	}
}
