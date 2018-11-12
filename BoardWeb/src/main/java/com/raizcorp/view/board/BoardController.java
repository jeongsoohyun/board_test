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
	
	// �� ���
	@RequestMapping ("/insertBoard.do")
	public String insertBoard (BoardVO vo) {
		// ����� �Է� ������ HttpServletRequest �� getParameter() �޼ҵ带 ����ؼ� �����ϸ� ������
		// �Է������� �������� �׿� ���� Controller Ŭ������ ������ �Ǿ�� �Ѵ�.
		// ������ Command ��ü�� �̿��ϸ� �� ������ �ذ� �� �� �ִ�.
		// Command ��ü�� Controller �޼ҵ� �Ű������� ���� VO ��ü��� ���� �ȴ�.
		
		// ��ó�� insertBoard �� �޼ҵ带 Command ��ü�� �����ϸ� ������ �����̳ʰ� insertBoard() �޼ҵ带 ������ �� Command ��ü�� �����ؼ� �Ѱ��ش�.
		// �̶� ����ڰ� �Է��� ������ Command ��ü�� �����ؼ� �Ѱ��ش�.
		System.out.println("�� ��� ó��");

		boardService.insertBoard(vo);
		
		return "getBoardList.do";
	}
	
	// �� ����
	@RequestMapping("/updateBoard.do")
	public String updateBoard (@ModelAttribute("board") BoardVO vo) {
		// @SessionAttributes ������� ����ڰ� ���� �Է����� ���� ���� ������ Command ��ü�� �־��ַ��� ���.
		// ����ڰ� �� ȭ��(getBoard.do)�� ��û�ϸ� getBoard() �޼ҵ�� �˻� ����� BoardVO ��ü�� board ��� �̸����� Model�� �����Ѵ�.
		// �̶� BoardController Ŭ������ ����� @SessionAttributes("board") ������ Model �� "board" ��� �̸����� ����Ǵ� �����Ͱ� �ִٸ�
		// �� �����и� ����(HttpSession)���� �ڵ����� �����϶�� ����.
		
		System.out.println("��ȣ : " + vo.getSeq());
		System.out.println("���� : " + vo.getTitle());
		System.out.println("�ۼ��� : " + vo.getWriter());
		System.out.println("���� : " + vo.getContent());
		System.out.println("����� : " + vo.getRegDate());
		System.out.println("��ȸ�� : " + vo.getCnt());

		boardService.updateBoard(vo);

		return "getBoardList.do";
	}
	
	// �� ����
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard (BoardVO vo) {
		
		boardService.deleteBoard(vo);

		return "getBoardList.do";
	}
	
	// �� �� ��ȸ
	@RequestMapping("/getBoard.do")
	public String getBoard (BoardVO vo, Model model) {
		
		model.addAttribute("board", boardService.getBoard(vo));

		return "getBoard.jsp";
	}
	
	// �� ��� �˻�
	@RequestMapping("/getBoardList.do")
	public String getBoardList (@RequestParam(value="searchCondition", defaultValue="TITLE", required=false) String condition,
								@RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword,
								BoardVO vo, Model model) {
		
		System.out.println("�˻� ���� : " + condition);
		System.out.println("�˻� �ܾ� : " + keyword);

		model.addAttribute("boardList", boardService.getBoardList(vo));
		
		// Session �� Ŭ���̾�Ʈ ������ �ϳ��� �ϳ��� ���� �޸𸮿� �����Ǿ� Ŭ���̾�Ʈ�� ���������� �����ϱ� ���� ���ǹǷ�
		// ���ǿ� ���� ������ ����Ǹ� �ɼ��� ������ �δ��� �ش�.
		// ���� Session ��ſ� ModelAndView ��ſ� HttpServletRequest ��ü�� �����ϴ°� �´�.
		// HttpServletRequest �� Ŭ���̾�Ʈ�� ��û���� �����Ǿ��ٰ� ���� �޼����� Ŭ���̾�Ʈ�� ���۵Ǹ� �ڵ����� �����Ǵ� ��ȸ�� ��ü�̹Ƿ�.
		// ������ HttpServletRequest ��ſ� ModelAndView ��ü�� �̿��� Model �� View ������ �Ѳ����� return �� ���� �����Ƿ�
		// ModelAndView �� �̿��Ѵ�.
		
		return "getBoardList.jsp";
	}
	
	// @ModelAttribute �� ������ �޼ҵ�� @RequestMapping �� ����� �޼ҵ庸�� ���� ȣ��ȴ�.
	// �׸��� @ModelAttribute �޼ҵ� ���� ����� ���ϵ� ��ü�� �ڵ����� Model�� ����ȴ�. ���� @ModelAttribute �޼ҵ��� ���� ����� ���ϵ� ��ü��
	// View ���������� ����� �� �ִ�.
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		conditionMap.put("����", "TITLE");
		conditionMap.put("����", "CONTENT");
		
		return conditionMap;
	}
}
