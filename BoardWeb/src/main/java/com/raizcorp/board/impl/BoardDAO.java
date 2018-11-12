package com.raizcorp.board.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.raizcorp.board.BoardVO;

// DAO(Data Access Object)
// DB 연동을 담당아흔 class. 따라서 CRUD(Create, Read, Update, Delet) 기능의 메소드가 구현되어야 함.

@Repository
public class BoardDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String BOARD_INSERT = "insert into board (title, write, content) values (?, ?, ?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete from board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	
	// CRUD 기능 구현
	// 글 등록
	public void insertBoard (BoardVO vo) {
		System.out.println("==> Spring JDBC로 insertBoard() 기능 처리");
		jdbcTemplate.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
	}

	// 글 수정
	public void updateBoard (BoardVO vo) {
		System.out.println("==> Spring JDBC로 updateBoard() 기능 처리");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent());
	}
	
	// 글 삭제
	public void deleteBoard (BoardVO vo) {
		System.out.println("==> Spring JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}
	
	// 글 상세 조회
	public BoardVO getBoard (BoardVO vo) {
		System.out.println("==> Spring JDBC로 getBoard() 기능 처리");
		
		Object[] args = {vo.getSeq()};
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	public Object getBoardList(BoardVO vo) {

		return null;
	}
}
