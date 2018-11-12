package com.raizcorp.board.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.raizcorp.board.BoardVO;

// DAO(Data Access Object)
// DB ������ ������ class. ���� CRUD(Create, Read, Update, Delet) ����� �޼ҵ尡 �����Ǿ�� ��.

@Repository
public class BoardDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String BOARD_INSERT = "insert into board (title, write, content) values (?, ?, ?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete from board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	
	// CRUD ��� ����
	// �� ���
	public void insertBoard (BoardVO vo) {
		System.out.println("==> Spring JDBC�� insertBoard() ��� ó��");
		jdbcTemplate.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
	}

	// �� ����
	public void updateBoard (BoardVO vo) {
		System.out.println("==> Spring JDBC�� updateBoard() ��� ó��");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent());
	}
	
	// �� ����
	public void deleteBoard (BoardVO vo) {
		System.out.println("==> Spring JDBC�� deleteBoard() ��� ó��");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}
	
	// �� �� ��ȸ
	public BoardVO getBoard (BoardVO vo) {
		System.out.println("==> Spring JDBC�� getBoard() ��� ó��");
		
		Object[] args = {vo.getSeq()};
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	public Object getBoardList(BoardVO vo) {

		return null;
	}
}
