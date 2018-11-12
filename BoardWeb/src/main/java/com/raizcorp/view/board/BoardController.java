package com.raizcorp.view.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.raizcorp.board.BoardService;
import com.raizcorp.board.BoardVO;
import com.raizcorp.board.impl.BoardDAO;

@Controller
@SessionAttributes("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// 글 등록
	@RequestMapping ("/insertBoard.do")
	public String insertBoard (BoardVO vo) {
		// 사용자 입력 정보는 HttpServletRequest 의 getParameter() 메소드를 사용해서 추출하면 되지만
		// 입력정보가 많아지면 그에 따라 Controller 클래스도 수정이 되어야 한다.
		// 하지만 Command 객체를 이용하면 이 문제를 해결 할 수 있다.
		// Command 객체란 Controller 메소드 매개변수로 받은 VO 객체라고 보면 된다.
		
		// 위처럼 insertBoard 의 메소드를 Command 객체로 선언하면 스프링 컨테이너가 insertBoard() 메소드를 실행할 때 Command 객체를 생성해서 넘겨준다.
		// 이때 사용자가 입력한 값들을 Command 객체에 세팅해서 넘겨준다.
		System.out.println("글 등록 처리");

		boardService.insertBoard(vo);
		
		return "getBoardList.do";
	}
	
	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard (@ModelAttribute("board") BoardVO vo) {
		// @SessionAttributes 사용으로 사용자가 직접 입력하지 않은 값을 가져와 Command 객체에 넣어주려고 사용.
		// 사용자가 상세 화면(getBoard.do)을 요청하면 getBoard() 메소드는 검색 결과인 BoardVO 객체를 board 라는 이름으로 Model에 저장한다.
		// 이때 BoardController 클래스에 선언된 @SessionAttributes("board") 설정은 Model 에 "board" 라는 이름으로 저장되는 데이터가 있다면
		// 그 데이털르 세션(HttpSession)에도 자동으로 저장하라는 설정.
		
		System.out.println("번호 : " + vo.getSeq());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("작성자 : " + vo.getWriter());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("등록일 : " + vo.getRegDate());
		System.out.println("조회수 : " + vo.getCnt());

		boardService.updateBoard(vo);

		return "getBoardList.do";
	}
	
	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard (BoardVO vo) {
		
		boardService.deleteBoard(vo);

		return "getBoardList.do";
	}
	
	// 글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard (BoardVO vo, Model model) {
		
		model.addAttribute("board", boardService.getBoard(vo));

		return "getBoard.jsp";
	}
	
	// 글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList (@RequestParam(value="searchCondition", defaultValue="TITLE", required=false) String condition,
								@RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword,
								BoardVO vo, Model model) {
		
		System.out.println("검색 조건 : " + condition);
		System.out.println("검색 단어 : " + keyword);

		model.addAttribute("boardList", boardService.getBoardList(vo));
		
		// Session 은 클라이언트 브라우저 하나당 하나씩 서버 메모리에 생성되어 클라이언트의 상태정보를 유지하기 위해 사용되므로
		// 세션에 많은 정보가 저장되면 될수록 서버에 부담을 준다.
		// 따라서 Session 대신에 ModelAndView 대신에 HttpServletRequest 객체에 저장하는게 맞다.
		// HttpServletRequest 는 클라이언트의 요청으로 생성되었다가 응답 메세지가 클라이언트로 전송되면 자동으로 삭제되는 일회성 객체이므로.
		// 하지만 HttpServletRequest 대신에 ModelAndView 객체를 이용해 Model 과 View 정보를 한꺼번에 return 할 수도 있으므로
		// ModelAndView 를 이용한다.
		
		return "getBoardList.jsp";
	}
	
	// @ModelAttribute 가 설정된 메소드는 @RequestMapping 이 적용된 메소드보다 먼저 호출된다.
	// 그리고 @ModelAttribute 메소드 실행 결과로 리턴된 객체는 자동으로 Model에 저장된다. 따라서 @ModelAttribute 메소드의 실행 결과로 리턴된 객체를
	// View 페이지에서 사용할 수 있다.
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		
		return conditionMap;
	}
}
