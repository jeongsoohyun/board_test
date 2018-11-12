package com.raizcorp.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("/logout.do")
	public String logout (HttpSession session) {
		// HttpSession �� �Ű������� �����ϸ� ������ �����̳ʰ� �α׾ƿ��� ��û�� �������� ���ε� ���� ��ü�� ã�Ƽ� �Ѱ���.
		// ���� �Ű������� ���� ���� ��ü�� ���� �����ϸ� �ȴ�.
		session.invalidate();

		return "login.jsp";
	}

}
