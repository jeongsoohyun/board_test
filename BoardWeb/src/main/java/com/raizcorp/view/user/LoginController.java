package com.raizcorp.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.raizcorp.user.UserVO;
import com.raizcorp.user.impl.UserDAO;

@Controller
public class LoginController {

	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo) {
		// Command ��ü(UserVO)�� �̸��� �����Ϸ��� �Ű����� ���� �տ� @ModelAttribute('user') �� �ٿ� jsp ���� '${user.id}' �������� ��� ����.
		System.out.println("�α��� ȭ������ �̵�");

		vo.setId("test");
		vo.setPassword("test123");
		
		return "login.jsp";
	}

	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
		
		System.out.println("�α��� ���� ó��...");
		
		UserVO user = userDAO.getUser(vo);
		
		System.out.println("UserName : " + user.getName());
		
		if (user != null) {
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
		
	}

}
