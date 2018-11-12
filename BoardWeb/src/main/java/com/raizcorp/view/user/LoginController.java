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
		// Command 객체(UserVO)의 이름을 변경하려면 매개변수 선언 앞에 @ModelAttribute('user') 을 붙여 jsp 에서 '${user.id}' 형식으로 사용 가능.
		System.out.println("로그인 화면으로 이동");

		vo.setId("test");
		vo.setPassword("test123");
		
		return "login.jsp";
	}

	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
		
		System.out.println("로그인 인증 처리...");
		
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
